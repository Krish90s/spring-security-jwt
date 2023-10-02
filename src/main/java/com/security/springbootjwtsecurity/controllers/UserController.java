package com.security.springbootjwtsecurity.controllers;

import com.security.springbootjwtsecurity.models.User;
import com.security.springbootjwtsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	
	@GetMapping
	public List<User> fetchAllUsers() {
		return userService.getAllUsers();
	}
}
