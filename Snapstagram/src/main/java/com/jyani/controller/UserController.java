package com.jyani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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


	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInUserDetailsHandler(Authentication auth){
		
		System.out.println(auth); // this Authentication object having Principle object details
		
		 User user= userService.getUserDetailsByUsername(auth.getName());
		 
		 return new ResponseEntity<>(user.getUsername()+" Logged In Successfully", HttpStatus.ACCEPTED);	
	}


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

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsersHandler() {
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

}
