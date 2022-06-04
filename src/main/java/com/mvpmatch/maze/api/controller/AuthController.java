package com.mvpmatch.maze.api.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mvpmatch.maze.api.config.JwtTokenUtil;
import com.mvpmatch.maze.api.model.User;
import com.mvpmatch.maze.api.repository.UserRepository;
import com.mvpmatch.maze.api.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws Exception {
		ResponseEntity<Map<String, Object>> response;
		Authentication authObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
			authenticationManager.authenticate(token);
			// SecurityContextHolder.getContext().setAuthentication(authObject);
			Map<String, Object> claims = new HashMap<>();
			String tokenData = jwtTokenUtil.doGenerateToken(claims, user.getEmail());
			data.put("token", tokenData);
			map.put("data", data);
			map.put("code", HttpStatus.OK);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (BadCredentialsException e) {
			data.put("error", e.getMessage());
			map.put("data", data);
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return response;
	}

	// @PostMapping("/login")
	// public ResponseEntity<Map<String, String>> login(@RequestBody User user) throws Exception {
	// 	ResponseEntity<Map<String, String>> response;
	// 	Authentication authObject = null;
	// 	Map<String, String> map = new HashMap<String, String>();
	// 	Response<Map<String, String>> responses;
	// 	try {
	// 		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
	// 		authenticationManager.authenticate(token);
	// 		// SecurityContextHolder.getContext().setAuthentication(authObject);
	// 		Map<String, Object> claims = new HashMap<>();
	// 		String tokenData = jwtTokenUtil.doGenerateToken(claims, user.getEmail());

	// 		map.put("data", tokenData);
	// 		Date d = new Date();
	// 		responses = new Response<Map<String, String>>(HttpStatus.OK, map, d);
	// 		response = new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);	
	// 	} catch (BadCredentialsException e) {
	// 		map.put("error", e.getMessage());
	// 		// throw new Exception("Invalid credentials");
	// 		response = new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

	// 	}
		
	// 	return response;
	// }


	@PostMapping("/user")
	public ResponseEntity<Map<String, Object>> register(@RequestBody User user) throws Exception {
		ResponseEntity<Map<String, Object>> response;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String msg = "User is already registered.";

			User existingUser =  userRepository.findByEmail(user.getEmail()).orElse(null);
			if(existingUser==null) {
				msg = "Registration is succesful.";
				userRepository.save(user);
			} else {
				throw new Exception(msg);
			}
			data.put("message", msg);
			map.put("data", data);
			map.put("code", HttpStatus.OK);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (Exception e) {
			data.put("error", e.getMessage());
			map.put("data", data);
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
}
