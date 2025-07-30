package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean checkLogin(String username, String password) {
		// 呼叫 Dao/Repository
		
		UserEntity user = userDao.findByUsernameAndPassword(username, password);
		if (user != null) {
			return true;
		} else {
			return false;
		}
		// return user != null;
	}
	
}
