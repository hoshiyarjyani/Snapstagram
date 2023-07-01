package com.jyani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jyani.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<com.jyani.model.User> opt = userRepository.findByUsername(username);

		if (opt.isPresent()) {

			com.jyani.model.User user = opt.get();

			List<GrantedAuthority> authorities = new ArrayList<>();

			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole().toString());
			authorities.add(sga);

			return new User(user.getUsername(), user.getPassword(), authorities);

		} else
			throw new BadCredentialsException("User Details not found with this username: " + username);
	}

}
