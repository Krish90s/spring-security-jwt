package com.security.springbootjwtsecurity.controllers;

import com.security.springbootjwtsecurity.dtos.RegisterUserBody;
import com.security.springbootjwtsecurity.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
	

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterUserBody requestBody){
		return authService.registerUser(requestBody);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody RegisterUserBody requestBody){
		return authService.login(requestBody);
	}
}
