package com.example.ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ehcache.service.UserService;

@Controller
public class UserProfile {

	@Autowired
	private UserService UserServiceImpl;
	
	@RequestMapping(value="/admin/user/getUserById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") int id){
		Object user=  UserServiceImpl.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
