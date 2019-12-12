package com.matrimony.cassini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String saveUser(User user) {
	 userRepository.save(user);
	 return Constant.REGISTRATION_SUCCESSFUL;
	}

}
