package com.dao;

import java.util.List;

import com.entity.UserEntity;

public interface UserDao {

	// Read
	public UserEntity findByUsernameAndPassword(String username, String password);
	
	// Create
	public void addUser(UserEntity userEnity);
	
	// Update
	public void updateUser(UserEntity userEnity);
	
	// Delete
	public void delUser(UserEntity userEnity);
	
	// 查出全部USER
	public List<UserEntity> findAll();
	
	public UserEntity findById(Long id);
}
