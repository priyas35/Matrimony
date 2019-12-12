package com.matrimony.cassini.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.dto.UserAcceptanceRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.RequestNotRaisedException;
import com.matrimony.cassini.exception.UserMappingNotFound;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.service.UserInterestService;

@RestController
@RequestMapping("/interest")
@CrossOrigin
public class UserInterestController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInterestController.class);

	
	/**
	 * This injects all the implementations in the userInterestService
	 */
	@Autowired
	UserInterestService userInterestService;

	/**
	 * 
	 * @param userId Here by passing the userId accepted details could be fetched
	 * 
	 * @return This returns the details of users who accepted the request
	 */

	@GetMapping("/{userId}/accepted")
	public ResponseEntity<List<User>> acceptedDetails(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok().body(userInterestService.acceptedDetails(userId));

	}

	/**
	 * This API is used to get the users while searching the opposite sex users
	 * 
	 * @param filterRequestDto 
	 * This filterRequestdto includes userID,occupation,religion,dateOfBirth
	 * 
	 * @return This returns the users as per the requirements after filtering occupation,dateOfBirth and religion
	 */

	@PostMapping
	public ResponseEntity<List<User>> getAllFilteredUsers(@RequestBody FilterRequestDto filterRequestDto) {
		return ResponseEntity.ok().body(userInterestService.getAllFilteredUsers(filterRequestDto));
	}
	/**
	 * This API is used to  send the request or show interest to opposite 
	 * user with the message request sent successfully
	 * 
	 * @param interestRequestDto This interestRequestDto contains fromUserId and toUserId in which the request 
	 * sent from one user to other user 
	 * @return This returns the interestResponseDto which contains statuscode and message
	 * @throws UserNotFoundException This exception occurs when toUserId is not found 
	 * while sending the request or showing interest
	 */

	@PostMapping("/request")
	public ResponseEntity<InterestResponseDto> showInterest(@RequestBody InterestRequestDto interestRequestDto)
			throws UserNotFoundException {
		return new ResponseEntity<>(userInterestService.showInterest(interestRequestDto), HttpStatus.CREATED);

	}
	/**
	 * This API is used to get the interested users along with their details
	 * 
	 * @param userId Here By passing the userId the interested users who are interested to accept the request are listed
	 * @return This returns the  list of interested users 
	 * @throws RequestNotRaisedException This exception occurs when request is not raised by the user
	 */

	@GetMapping("/{userId}/interest")
	public ResponseEntity<List<Optional<User>>> getInterestedList(Integer userId) throws RequestNotRaisedException {
		List<Optional<User>> users = userInterestService.requestList(userId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	/**
	 * This API is used to accept or deny the request 
	 * 
	 * @param userAcceptanceRequestDto includes fromUserId,toUserId,statuscode
	 * @return This returns the message as rejected or accepted
	 * @throws UserMappingNotFound This exception occurs when user mapping not found
	 * @throws RequestNotRaisedException This exception occurs when request is not raised by the user
	 */

	@PutMapping
	public ResponseEntity<String> userResponse(@RequestBody UserAcceptanceRequestDto userAcceptanceRequestDto)
			throws UserMappingNotFound, RequestNotRaisedException {
		String result = userInterestService.userResponse(userAcceptanceRequestDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
