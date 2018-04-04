package com.igt.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igt.poc.dao.UserDao;

@Service("UserService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public Boolean findByUserNamePassword(String userName, String password) {
		Boolean flag= userDao.findByUserNamePassword(userName, password);
		return flag;
	}

}
