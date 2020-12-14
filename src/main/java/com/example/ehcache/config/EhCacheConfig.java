package com.example.ehcache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;

@Configuration
@EnableCaching
public class EhCacheConfig implements CachingConfigurer {

	@Value("${eh.cache.name:userProfile}")
	private String cacheName;

	@Value("${eh.cache.eviction.policy:LRU}")
	private String memoryStoreEvictionPolicy;

	@Value("${eh.cache.heap.object.count:1}")
	private int maxEntriesLocalHeap;

	@Value("${eh.cache.disk.limit:1}")
	private int cacheDiskLimitGb;

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager(){
		PersistenceConfiguration pConfig = new PersistenceConfiguration();
		pConfig.strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP);
		
		CacheConfiguration cacheConfig = new CacheConfiguration();
		cacheConfig.setName(cacheName);
		cacheConfig.setMemoryStoreEvictionPolicy(memoryStoreEvictionPolicy);
		cacheConfig.setMaxEntriesLocalHeap(maxEntriesLocalHeap);
		cacheConfig.setEternal(true);
		cacheConfig.maxBytesLocalDisk(cacheDiskLimitGb, MemoryUnit.GIGABYTES);
		cacheConfig.addPersistence(pConfig);
		
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfig);
		
		return net.sf.ehcache.CacheManager.newInstance(config);
	}
	
	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	@Bean
	@Override
	public CacheResolver cacheResolver() {
		return new SimpleCacheResolver(cacheManager());
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		return new SimpleCacheErrorHandler();
	}
}
