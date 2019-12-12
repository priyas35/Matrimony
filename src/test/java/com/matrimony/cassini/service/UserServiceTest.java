package com.matrimony.cassini.service;

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
import com.matrimony.cassini.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	LoginRequestDto loginRequestDto = null;
	User user = null;

	@Test
	public void testuserLogin() throws UserNotFoundException {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("yoga");
		loginRequestDto.setPassword("india");

		user = new User();
		user.setUserName("yoga");
		user.setPassword("india");

		Mockito.when(userRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(user));
		Optional<User> users = userServiceImpl.userLogin(loginRequestDto);
		assertNotNull(users);
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
		user = new User();
		user.setUserId(1);
		user.setCity("bng");
		user.setDateOfBirth(LocalDate.now());
		user.setEmail("yoga@gmail.com");
		user.setFullName("yoga");
		user.setGender("male");
		user.setHeight(6.1);
		user.setMotherTongue("tel");
		user.setPassword("india");
		user.setUserName("yogaa");
		user.setOccupation("doc");
		user.setReligion("hind");

		RegisterResponseDto registerResponseDto = new RegisterResponseDto();
		registerResponseDto.setMessage("success");
		registerResponseDto.setStatusCode(200);

		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		RegisterResponseDto user = userServiceImpl.saveUser(userRegistrationRequestDto);
		assertNotNull(user);

	}

}
