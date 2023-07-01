package com.jyani.service;

import java.util.List;

import com.jyani.exception.UserException;
import com.jyani.model.User;

public interface UserService {
   
	public User createUser(User user) throws UserException;
	
	public List<User> getAllUsers() throws UserException;
	
	
}
