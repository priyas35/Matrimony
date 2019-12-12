package com.matrimony.cassini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User userLogin(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByUserNameAndPassword(loginRequestDto.getUserName(), loginRequestDto.getPassword());
		return user;
	}
	@Override
	public String saveUser(User user) {
	 userRepository.save(user);
	 return Constant.REGISTRATION_SUCCESSFUL;
	}

}
