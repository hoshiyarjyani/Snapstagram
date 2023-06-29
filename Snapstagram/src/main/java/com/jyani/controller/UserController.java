package com.jyani.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jyani.model.User;
import com.jyani.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUserHandler(@RequestBody User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.ACCEPTED);
	}

}
