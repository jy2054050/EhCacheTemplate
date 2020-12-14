package com.example.ehcache.service;

import java.util.Map;

import com.example.ehcache.model.UserInformation;

public interface UserService {
	public UserInformation getUserById(int id);
	public Map<String, Integer> getCacheStats();
}
