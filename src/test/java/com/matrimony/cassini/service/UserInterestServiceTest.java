package com.matrimony.cassini.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserInterestRepository;
import com.matrimony.cassini.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInterestServiceTest {
	
	@Mock
	private UserInterestRepository userInterestRepository;
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserInterestServiceImpl userInterestServiceImpl;
	
	User user = null;
	User user1 = null;
	UserInterest userInterest = null;
	List<UserInterest> userIntrestList = null;
	InterestRequestDto interestRequestDto = null;
	@Before
	public void setUp() {
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
	}
 	@Test
	public void testAcceptedDetails() {
 		userIntrestList = new ArrayList<>();
 		userInterest = new UserInterest();
 		userInterest.setFromUser(user1);
 		userInterest.setToUser(user);
 		userInterest.setMapId(1);
 		userInterest.setDate(LocalDate.now());
 		userInterest.setStatus("Approved");
 		userIntrestList.add(userInterest);
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
		
		user1 = new User();
		user1.setUserId(1);
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(userInterestRepository.findByFromUserAndStatus(Mockito.any(), Mockito.anyString())).thenReturn(userIntrestList);
		List<User> userslist = userInterestServiceImpl.acceptedDetails(1);
		assertNotNull(userslist);
	}
 	@Test
 	public void showInterest() throws UserNotFoundException {
 		interestRequestDto = new InterestRequestDto();
 		interestRequestDto.setFromUserId(1);
 		interestRequestDto.setToUserId(2);
 		InterestResponseDto interestResponseDto = new InterestResponseDto();
 		interestResponseDto.setMessage("success");
 		interestResponseDto.setStatusCode(200);
 		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
 		Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user));
 		interestResponseDto = userInterestServiceImpl.showInterest(interestRequestDto);
 		assertNotNull(interestResponseDto);
 	}
 	
}
