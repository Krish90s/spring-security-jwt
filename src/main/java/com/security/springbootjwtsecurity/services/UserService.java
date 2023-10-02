package com.security.springbootjwtsecurity.services;

import com.security.springbootjwtsecurity.models.User;
import com.security.springbootjwtsecurity.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	
	private UserRepositories userRepositories;
	
	@Autowired
	public UserService(UserRepositories userRepositories) {
		this.userRepositories = userRepositories;
	}
	
	public List<User> getAllUsers() {
		return userRepositories.findAll();
	}
}
