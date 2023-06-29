package com.jyani.service;

import com.jyani.exception.UserException;
import com.jyani.model.User;

public interface UserService {
   
	public User createUser(User user) throws UserException;
	
}
