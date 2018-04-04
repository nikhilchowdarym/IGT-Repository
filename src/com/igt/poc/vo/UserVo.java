package com.igt.poc.vo;

import org.springframework.data.mongodb.core.mapping.Field;

public class UserVo {

	
	@Field("UserName")
	private String userName;
	
	@Field("Password")
	private String password;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
