package com.matrimony.cassini.service;

import java.util.Optional;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.entity.User;

public interface UserService {
	
	public String saveUser(User user);
	

	public Optional<User> userLogin(LoginRequestDto loginRequestDto);

}
