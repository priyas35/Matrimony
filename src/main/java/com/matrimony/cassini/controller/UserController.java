package com.matrimony.cassini.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	/**
	 * This will inject all the implementations in the userservice
	 */

	@Autowired
	UserService userService;

	/**
	 * This API is used to login the user
	 * 
	 * @param loginRequestDto which includes userName and password
	 * 
	 * @return This returns the user details when logged in into the application
	 * 
	 * @throws UserNotFoundException This exception occurs when user gives invalid
	 *                               username and password
	 */

	@PostMapping("login")
	public ResponseEntity<Optional<User>> userLogin(@RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		logger.info("for login");
		return ResponseEntity.ok().body(userService.userLogin(loginRequestDto));
	}

	/**
	 * This API has the method saveUser in which the users can register their
	 * profile
	 * 
	 * @param user Here user details are given as the responsebody
	 * @return This returns the registerResponseDto which includes statusCode and
	 *         message
	 */
	@PostMapping
	public ResponseEntity<RegisterResponseDto> saveUser(
			@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
		logger.info("to register the profile");
		return ResponseEntity.ok().body(userService.saveUser(userRegistrationRequestDto));
	}

}
