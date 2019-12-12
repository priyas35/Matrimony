package com.matrimony.cassini.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
import com.matrimony.cassini.repository.UserInterestRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserInterestServiceTest {
	
	@Mock
	private UserInterestRepository userInterestRepository;
	
	@InjectMocks
	private UserInterestServiceImpl userInterestServiceImpl;
	
	User user = null;
	User user1 = null;
	UserInterest userInterest = null;
	List<UserInterest> userIntrestList = null;
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
		
		Mockito.when(userInterestRepository.findByFromUserAndStatus(Mockito.any(), Mockito.anyString())).thenReturn(userIntrestList);
		List<User> userslist = userInterestServiceImpl.acceptedDetails(1);
		assertNotNull(userslist);
	}
}
