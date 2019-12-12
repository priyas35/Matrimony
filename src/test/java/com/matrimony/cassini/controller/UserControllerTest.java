package com.matrimony.cassini.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.matrimony.cassini.dto.LoginRequestDto;
import com.matrimony.cassini.dto.RegisterResponseDto;
import com.matrimony.cassini.dto.UserRegistrationRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	@Mock
	private UserService userServiceImpl;

	@InjectMocks
	private UserController userController;

	LoginRequestDto loginRequestDto = null;
	User user = null;

	@Test
	public void testUserLogin() throws UserNotFoundException {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("yoga");
		loginRequestDto.setPassword("india");

		user = new User();
		user.setUserName("yoga");
		user.setPassword("india");
		Mockito.when(userServiceImpl.userLogin(loginRequestDto)).thenReturn(Optional.of(user));
		ResponseEntity<Optional<User>> user = userController.userLogin(loginRequestDto);
		assertNotNull(user);

	}

	@Test
	public void testSaveUser() {
		UserRegistrationRequestDto userRegistrationRequestDto = new UserRegistrationRequestDto();
		userRegistrationRequestDto.setCity("bng");
		userRegistrationRequestDto.setDateOfBirth(LocalDate.now());
		userRegistrationRequestDto.setEmail("yoga@gmail.com");
		userRegistrationRequestDto.setFullName("yoga");
		userRegistrationRequestDto.setGender("male");
		userRegistrationRequestDto.setHeight(6.1);
		userRegistrationRequestDto.setMotherTongue("tel");
		userRegistrationRequestDto.setPassword("india");
		userRegistrationRequestDto.setUserName("yogaa");
		userRegistrationRequestDto.setOccupation("doc");
		userRegistrationRequestDto.setReligion("hind");
		userRegistrationRequestDto.setQualification("bt");

		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		registerResponseDto.setMessage("success");
		registerResponseDto.setStatusCode(200);

		Mockito.when(userServiceImpl.saveUser(userRegistrationRequestDto)).thenReturn(registerResponseDto);
		ResponseEntity<RegisterResponseDto> user = userController.saveUser(userRegistrationRequestDto);
		assertNotNull(user);

	}

}
