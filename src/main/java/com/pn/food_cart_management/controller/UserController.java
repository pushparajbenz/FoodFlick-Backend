package com.pn.food_cart_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pn.food_cart_management.entity.AuthRequest;
import com.pn.food_cart_management.entity.UserInfo;
import com.pn.food_cart_management.repository.UserInfoRepository;
import com.pn.food_cart_management.service.JwtService;
import com.pn.food_cart_management.service.UserInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("auth")
public class UserController {

	@Autowired
	private UserInfoService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInfoRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/addNewUser")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		logger.info("Adding new user: {}", userInfo);
		String response = service.addUser(userInfo);
		logger.info("User added successfully: {}", userInfo);
		return response;
	}

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		logger.info("Authenticating user: {}", authRequest.getUsername());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			logger.info("User authenticated successfully: {}", authRequest.getUsername());
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			logger.error("Invalid user request: {}", authRequest.getUsername());
			throw new RuntimeException("invalid user request !");
		}
	}

	@GetMapping("/admin/userdetails")
	public List<UserInfo> getProfiles() {
		logger.info("Fetching all user profiles");
		List<UserInfo> users = service.getUser();
		logger.info("Fetched user profiles: {}", users);
		return users;
	}

	@GetMapping("/user/profile")
	public UserInfo userProfile(@RequestHeader("Authorization") String authHeader) {
		String token = null;
		String username = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}
		logger.info("Fetching profile for user: {}", username);
		Optional<UserInfo> userDetail = repository.findByEmailId(username);
		if (userDetail.isPresent()) {
			logger.info("User profile fetched successfully: {}", userDetail.get());
			return userDetail.get();
		} else {
			logger.error("User profile not found for username: {}", username);
			throw new RuntimeException("User profile not found");
		}
	}
}
