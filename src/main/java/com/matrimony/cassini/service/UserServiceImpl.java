package com.matrimony.cassini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User userLogin(LoginRequestDto loginRequestDto) {
		User user = userRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),
				loginRequestDto.getPassword());
		return user;

	}

	@Override
	public RegisterResponseDto saveUser(UserRegistrationRequestDto userRegistrationRequestDto) {
		User user = new User();
		user.setFullName(userRegistrationRequestDto.getFullName());
		user.setCity(userRegistrationRequestDto.getCity());
		user.setDateOfBirth(userRegistrationRequestDto.getDateOfBirth());
		user.setEmail(userRegistrationRequestDto.getEmail());
		user.setGender(userRegistrationRequestDto.getGender());
		user.setHeight(userRegistrationRequestDto.getHeight());
		user.setMotherTongue(userRegistrationRequestDto.getMotherTongue());
		user.setOccupation(userRegistrationRequestDto.getOccupation());
		user.setPassword(userRegistrationRequestDto.getPassword());
		user.setQualification(userRegistrationRequestDto.getQualification());
		user.setReligion(userRegistrationRequestDto.getReligion());
		user.setUserName(userRegistrationRequestDto.getUserName());
		userRepository.save(user);
		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		registerResponseDto.setMessage("success");
		registerResponseDto.setStatusCode(200);
		return registerResponseDto;
	}

}
