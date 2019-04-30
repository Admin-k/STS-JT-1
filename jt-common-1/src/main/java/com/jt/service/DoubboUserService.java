package com.jt.service;

import com.jt.pojo.User;

public interface DoubboUserService {

	void saveUser(User user);
	
	String findUserById(User user);

}
