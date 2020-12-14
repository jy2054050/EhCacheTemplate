package com.example.ehcache.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.ehcache.model.UserInformation;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Component
public class UserCacheBuilderUtil {

	public void buildUserCacheFromFile(String fileName, Cache userCache){
		File file = new File(fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				String[] firstSplit = line.split("-");
				int id = Integer.valueOf(firstSplit[0]);
				String[] secondSplit = firstSplit[1].split(",");
				String name = secondSplit[0];
				char gender = Character.valueOf(secondSplit[1].charAt(0));
				int age = Integer.valueOf(secondSplit[2]);
				UserInformation user = new UserInformation(id, name, gender, age);
				updateUserCache(userCache, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateUserCache(Cache userCache, UserInformation user) {
		userCache.put(new Element(user.getId(), user));
	}
}
