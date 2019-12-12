package com.matrimony.cassini.service;

import java.util.Optional;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;

public interface UserService {
	
	public RegisterResponseDto saveUser(User user);
	

	public Optional<User> userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException;

}
