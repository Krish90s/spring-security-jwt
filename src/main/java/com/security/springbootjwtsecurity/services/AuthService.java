package com.security.springbootjwtsecurity.services;

import com.security.springbootjwtsecurity.config.JwtService;
import com.security.springbootjwtsecurity.dtos.RegisterUserBody;
import com.security.springbootjwtsecurity.models.User;
import com.security.springbootjwtsecurity.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
	

	private final UserRepositories userRepositories;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	
	public ResponseEntity<String> registerUser(RegisterUserBody registerUserBody){
		User user = userRepositories.findByEmail(registerUserBody.getEmail());
		
		if(user != null){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("user already exist with email id %s already exist", registerUserBody.getEmail()));
		}
		
		User newUser = new User();
		newUser.setHashedPassword(passwordEncoder.encode(registerUserBody.getPassword()));
		newUser.setEmail(registerUserBody.getEmail());
		
		userRepositories.save(newUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("user registered successfully");
	}
	
	public ResponseEntity<String> login(RegisterUserBody registerUserBody){
		User user = userRepositories.findByEmail(registerUserBody.getEmail());
		
		if(user == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist.");
		}
		
		if(checkPassword(registerUserBody.getPassword(), user.getHashedPassword())){
			String token = jwtService.generateToken(user);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + token);
			
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body("login successfully");
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Password");
	}
	
	public boolean checkPassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
