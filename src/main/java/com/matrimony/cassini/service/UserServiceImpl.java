package com.matrimony.cassini.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	/**
	 * This will inject all the implmentations in the userRepository
	 */

	@Autowired
	private UserRepository userRepository;
	/**
	 * This method is used to login the user
	 * loginRequestDto includes userName and password
	 * 
	 * This method returns the user details when logged in into the application
	 * 
	 * UserNotFoundException exception occurs when user gives invalid username and password
	 */

	@Override
	public Optional<User> userLogin(LoginRequestDto loginRequestDto) throws UserNotFoundException {
		Optional<User> user = userRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword());
		if (user.isPresent()) {
			return user;
		} else {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
			
		}

	
	 /**
     * This method has the method saveUser in which the users can register their profile in the 
     * application by entering the user details and saving the user
     * 
     * This method returns the registerResponseDto which includes statusCode and message
     */

	@Override
	public RegisterResponseDto saveUser(UserRegistrationRequestDto userRegistrationRequestDto) {
		userRepository.save(user);
		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		registerResponseDto.setMessage("success");
		registerResponseDto.setStatusCode(200);
		return registerResponseDto;
	}

}
