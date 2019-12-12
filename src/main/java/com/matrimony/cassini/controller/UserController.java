package com.matrimony.cassini.controller;

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
import com.matrimony.cassini.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("login")
	public ResponseEntity<User> userLogin(@RequestBody LoginRequestDto loginRequestDto) {
		return ResponseEntity.ok().body(userService.userLogin(loginRequestDto));
	}

	@PostMapping
	public ResponseEntity<RegisterResponseDto> saveUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
		return ResponseEntity.ok().body(userService.saveUser(userRegistrationRequestDto));
	}

}
