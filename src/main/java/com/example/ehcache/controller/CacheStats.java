package com.example.ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ehcache.service.UserService;

@Controller
public class CacheStats {

	@Autowired
	private UserService UserServiceImpl;

	@RequestMapping(value="/admin/health", method = RequestMethod.GET)
	public ResponseEntity<?> health(){
		return new ResponseEntity<>(new String ("OKAY"), HttpStatus.OK);
	}
	
	@RequestMapping(value="/admin/cacheStats", method = RequestMethod.GET)
	public ResponseEntity<?> getCacheStats(){
		Object stats =  UserServiceImpl.getCacheStats();
		return new ResponseEntity<>(stats, HttpStatus.OK);
	}
	
}
