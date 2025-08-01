package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.UserEntity;
import com.vo.UserVo;

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
	
	public void addUser(String username, String password) {
		UserEntity entity = new UserEntity();
		entity.setUsername(username);
		entity.setPassword(password);
		
		userDao.addUser(entity);
		
	}
	
	public List<UserEntity> findAllUser() {
		List<UserEntity> userList = userDao.findAll();
		
		// 如果有需要轉換物件，在這裡做
		
		
		return userList;
	}
	
	
	public void deleteUserById(Long id) {
		// 透過ID 找到 UserEntity
		UserEntity entity = userDao.findById(id);
		
		userDao.delUser(entity);
		
	}
	
	public UserVo findUserById(Long id) {
		// 透過ID 找到 UserEntity
		UserEntity entity = userDao.findById(id);

		UserVo vo = new UserVo();
		vo.setId(entity.getId());
		vo.setUsername(entity.getUsername());
		vo.setPassword(entity.getPassword());
		vo.setEmail(entity.getEmail());
		if (entity.getBirthDay() != null) {
			vo.setBirthDay(parseDateToString(entity.getBirthDay()));			
		} else {
			vo.setBirthDay("");
		}
		
		return vo;
	}
	
	private Date parseDate(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateStr);
			System.out.println("Parsed Date: " + date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 日期轉換字串
	 * @param date
	 * @return
	 */
	private String parseDateToString(Date date) {
	     // 定義日期格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // 將 Date 物件轉換為 String
        return formatter.format(date);
	}
	
	public void upateUser(UserVo userVo) {
		UserEntity user = userDao.findById(Long.valueOf(userVo.getId()));

		// "", "    "
		if (userVo.getPassword() != null && !userVo.getPassword().isBlank()) {
			user.setPassword(userVo.getPassword());
		}

		if (userVo.getEmail() != null && !userVo.getEmail().isBlank()) {
			user.setEmail(userVo.getEmail());
		}

		if (userVo.getBirthDay() != null && !userVo.getBirthDay().isBlank()) {
			user.setBirthDay(parseDate(userVo.getBirthDay()));
		}

		userDao.updateUser(user);
	}
	
}
