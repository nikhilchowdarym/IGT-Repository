package com.igt.poc.dao;

public interface UserDao {
	
	public Boolean findByUserNamePassword(String userName,String password);

}
