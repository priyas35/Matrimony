package com.matrimony.cassini.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.dto.UserAcceptanceRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
import com.matrimony.cassini.exception.RequestNotRaisedException;
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
	UserAcceptanceRequestDto userAcceptanceRequestDto = null;
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
 	
 	
 	
 	@Test
 	public void userResponseTest() throws RequestNotRaisedException, UserNotFoundException {
 		
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
		user1.setUserId(2);
		user1.setCity("bng");
		user1.setDateOfBirth(LocalDate.now());
		user1.setEmail("yoga@gmail.com");
		user1.setFullName("yoga");
		user1.setGender("Female");
		user1.setHeight(6.1);
		user1.setMotherTongue("tel");
		user1.setPassword("india");
		user1.setUserName("yogaa");
		user1.setOccupation("doc");
		user1.setReligion("hind");
 		userInterest = new UserInterest();
 		userInterest.setFromUser(user);
 		userInterest.setToUser(user1);
 		userInterest.setMapId(1);
 		userInterest.setDate(LocalDate.now());
 		userInterest.setStatus(Constant.REQUESTED);
 		userAcceptanceRequestDto=new UserAcceptanceRequestDto();
 		userAcceptanceRequestDto.setFromUserId(user1.getUserId());
 		userAcceptanceRequestDto.setToUserId(user.getUserId());
 		userAcceptanceRequestDto.setStatusCode(1);
 		Mockito.when(userRepository.findById(2)).thenReturn(Optional.of(user1));
 		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
 		Mockito.when(userInterestRepository.findByFromUserAndToUser(user, user1)).thenReturn(Optional.of(userInterest));
 		userInterest.setStatus(Constant.ACCEPTED);
 		userInterest.setDate(LocalDate.now());
 		UserInterest userInterest1=new UserInterest();
 		userInterest1.setFromUser(user1);
 		userInterest1.setToUser(user);
 		userInterest1.setMapId(1);
 		userInterest1.setDate(LocalDate.now());
 		userInterest1.setStatus(Constant.ACCEPTED);
 		String actual=userInterestServiceImpl.userResponse(userAcceptanceRequestDto);
 		assertEquals("Accepted", actual);
 		
 		
 	}
 	
 	
 	
 	@Test
 	public void  getAllFilteredUsersTest() {
 	  List<User> users = new ArrayList<User>();
 	  User user = new User();
 	  user.setUserId(1);
 	  users.add(user);
 	  List<UserInterest> userInterests = new ArrayList<UserInterest>();
 	  UserInterest userInterest = new UserInterest();
 	  userInterest.setFromUser(user);
 	  userInterest.setToUser(user);
 	  userInterests.add(userInterest);
 	  Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
 	Mockito.when(userRepository.findByGenderNot(Mockito.any())).thenReturn(users);
 	Mockito.when(userInterestRepository.findByToUser(Mockito.any())).thenReturn(userInterests);
 	Mockito.when(userInterestRepository.findByFromUserAndStatus(Mockito.any(), Mockito.any())).thenReturn(userInterests);
 	assertNotNull(userInterestServiceImpl.getAllFilteredUsers(new FilterRequestDto()));
 	}
 	
}
