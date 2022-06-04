package com.mvpmatch.maze.api.service;

import java.util.ArrayList;

import com.mvpmatch.maze.api.model.User;
import com.mvpmatch.maze.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> 
				new UsernameNotFoundException("User not found"));
		
		return new org.springframework.security.core.userdetails.User(
				existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
	}

}
