package com.matrimony.cassini.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserInterestService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInterestControllerTest {
	
	@InjectMocks
	UserInterestController userInterestController;
	@Mock
	UserInterestService userInterestService;
	
	
	User user = null;
	List<User> userlist = null;
	@Test
	public void testAcceptedDetails() {
		userlist = new ArrayList<>();
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
		userlist.add(user);
		Mockito.when(userInterestService.acceptedDetails(1)).thenReturn(userlist);
		ResponseEntity<List<User>> users = userInterestController.acceptedDetails(1);
		assertNotNull(users);
	}
	
	@Test
	public void testShowInterest() throws UserNotFoundException {
		InterestRequestDto interestRequestDto=new InterestRequestDto();
		InterestResponseDto interestResponseDto=new  InterestResponseDto();
		Mockito.when(userInterestService.showInterest(interestRequestDto)).thenReturn(interestResponseDto);
		ResponseEntity<InterestResponseDto> response=userInterestController.showInterest(interestRequestDto);
		Assert.assertNotNull(response);
		

	}
}
