package com.dao;

import com.entity.UserEntity;

public interface UserDao {

	public UserEntity findByUsernameAndPassword(String username, String password);
}
