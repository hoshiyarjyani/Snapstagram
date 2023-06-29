package com.jyani.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyani.exception.UserException;
import com.jyani.model.User;
import com.jyani.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws UserException {
		Optional<User> opt = userRepository.findByEmail(user.getEmail());
		if (opt.isEmpty()) {
			userRepository.save(user);
		} else {
			throw new UserException("User Already Exists with this Email Id. Try With New One");
		}
		return user;
	}

}
