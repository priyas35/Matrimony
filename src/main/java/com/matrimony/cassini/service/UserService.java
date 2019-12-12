package com.matrimony.cassini.service;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.entity.User;

public interface UserService {
	
	public RegisterResponseDto saveUser(User user);
	

	public User userLogin(LoginRequestDto loginRequestDto);

}
