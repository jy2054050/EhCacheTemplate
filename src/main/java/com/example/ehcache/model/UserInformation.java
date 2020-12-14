package com.example.ehcache.model;

import java.io.Serializable;

public class UserInformation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private char gender;
	private int age;
	
	public UserInformation(int id2, String name2, char gender2, int age2) {
		this.id= id2;
		this.name= name2;
		this.gender = gender2;
		this.age = age2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserInformation [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
}
