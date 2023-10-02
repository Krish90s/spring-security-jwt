package com.security.springbootjwtsecurity.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserBody {
	private String email;
	private String password;
}
