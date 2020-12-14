package com.example.ehcache.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.ehcache.model.UserInformation;
import com.example.ehcache.service.UserService;
import com.example.ehcache.utils.UserCacheBuilderUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private CacheManager cacheManger;
	
	private Cache userCache;
	
	@Value("${eh.cache.name:userProfile}")
	private String cacheName;
	
	@Autowired
	private UserCacheBuilderUtil userCacheBuilderUtil;
	
	private final String userStaticFile = "users-static-data.txt";
	
	@PostConstruct
	public void init() {
		userCache = cacheManger.getCache(cacheName);
		userCacheBuilderUtil.buildUserCacheFromFile(userStaticFile, userCache);
	}
	
	@Override
	public UserInformation getUserById(int id) {
		return this.getUserFromCache(id);
	}

	private UserInformation getUserFromCache(int id) {
		Element element = userCache.get(id);
		UserInformation userRetreived = null;
		if (null != element) {
			userRetreived = (UserInformation) element.getObjectValue();
		}
		return userRetreived;
	}

	@Override
	public Map<String, Integer> getCacheStats() {
		Map<String, Integer> cacheStats =  new HashMap<>();
		cacheStats.put("User Cache Size", userCache.getSize());
		return cacheStats;
	}
}
