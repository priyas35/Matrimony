package com.matrimony.cassini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.dto.UserAcceptanceRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
import com.matrimony.cassini.exception.RequestNotRaisedException;
import com.matrimony.cassini.exception.UserMappingNotFound;
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserInterestRepository;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserInterestServiceImpl implements UserInterestService {
	/**
	 * This will inject all the implementations in the userRepository
	 */

	@Autowired
	UserRepository userRepository;
	/**
	 * This will inject all the implementations in the userInterestRepository
	 */

	@Autowired
	UserInterestRepository userInterestRepository;
	/**
	 * This API is used to get the users while searching the opposite sex users
	 * filterRequestdto includes userID,occupation,religion,dateOfBirth 
	 * This returns the users as per the requirements after filtering occupation,dateOfBirth and religion
	 */

	@Override
	public List<User> getAllFilteredUsers(FilterRequestDto filterRequestDto) {
		Optional<User> user = userRepository.findById(filterRequestDto.getUserId());
		if (user.isPresent()) {
			List<User> users = userRepository.findByGenderNot(user.get().getGender());
			users = users.stream().filter(user1 -> user1.getOccupation().equals(filterRequestDto.getOccupation())
					&& user1.getReligion().equals(filterRequestDto.getReligion())).collect(Collectors.toList());

			if (filterRequestDto.getOccupation() != null) {
				users = users.stream().filter(user1 -> user1.getOccupation().equals(filterRequestDto.getOccupation()))
						.collect(Collectors.toList());
			}
			if (filterRequestDto.getReligion() != null) {
				users = users.stream().filter(user1 -> user1.getReligion().equals(filterRequestDto.getReligion()))
						.collect(Collectors.toList());
			}
			if (filterRequestDto.getDateOfBirth() != null) {
				users = users.stream()
						.filter(user1 -> user1.getDateOfBirth().isAfter(filterRequestDto.getDateOfBirth()))
						.collect(Collectors.toList());
			}
			return users;
		} else {
			return new ArrayList<>();
		}
	}
	/**
	 * Here by passing the userId the users who accepted the request their details could be 
	 * listed by finding the sender and their status
	 * 
	 */

	@Override
	public List<User> acceptedDetails(Integer userId) {
		List<User> users = new ArrayList<>();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			List<UserInterest> userMappings = userInterestRepository.findByFromUserAndStatus(user.get(),
					Constant.ACCEPTED);
			for (UserInterest UserMapping : userMappings) {
				users.add(UserMapping.getToUser());
			}
		}
		return users;
	}
	/**
	 * This method is used to  send the request or show interest to opposite 
	 * user with the message request sent successfully
	 * 
	 * interestRequestDto contains fromUserId and toUserId in which the request 
	 * sent from one user to other user 
	 * 
	 * This method returns the interestResponseDto which contains statuscode and message
	 * UserNotFoundException occurs when toUserId is not found 
	 * while sending the request or showing interest
	 */

	public InterestResponseDto showInterest(InterestRequestDto interestRequestDto) throws UserNotFoundException {
		Optional<User> sender = userRepository.findById(interestRequestDto.getFromUserId());
		Optional<User> receiver = userRepository.findById(interestRequestDto.getToUserId());
		if (!sender.isPresent()) {
			throw new UserNotFoundException(Constant.SENDER_PROFILE_NOT_FOUND);
		}
		if (!receiver.isPresent()) {
			throw new UserNotFoundException(Constant.RECEIVER_PROFILE_NOT_FOUND);
		}
		UserInterest interest = new UserInterest();
		interest.setStatus("interested");
		interest.setFromUser(sender.get());
		interest.setToUser(receiver.get());
		userInterestRepository.save(interest);
		InterestResponseDto interestResponseDto = new InterestResponseDto();
		interestResponseDto.setMessage(Constant.REQUESTED);
		interestResponseDto.setStatusCode(Constant.OK);
		return interestResponseDto;

	}
	/**
	 * This method is used to get the interested users along with their details
	 * 
	 * Here By passing the userId the interested users who are interested to accept the request are listed
	 * This method returns the  list of interested users 
	 * RequestNotRaisedException occurs when request is not raised by the user
	 */

	@Override
	public List<Optional<User>> requestList(Integer userId) throws RequestNotRaisedException {
		Optional<User> currentuser = userRepository.findById(userId);
		List<UserInterest> userInterests = userInterestRepository.findAllUserMappingsByToUserAndStatus(currentuser,
				Constant.REQUESTED);
		List<Optional<User>> users = new ArrayList<Optional<User>>();
		if (userInterests.isEmpty()) {
			throw new RequestNotRaisedException(Constant.REQUEST_NOT_RAISED);
		} else {
			for (UserInterest userInterest : userInterests) {
				Optional<User> interresteduser = userRepository.findById(userInterest.getFromUser().getUserId());
				users.add(interresteduser);
			}
			return users;
		}

	}
	/**
	 * This method is used to accept or deny the request 
	 * userAcceptanceRequestDto includes fromUserId,toUserId,statuscode
	 * 
	 * This method returns the message as rejected or accepted
	 * 
	 * UserMappingNotFound exception occurs when user mapping not found
	 * RequestNotRaisedException occurs when request is not raised by the user
	 */

	@Override
	public String userResponse(UserAcceptanceRequestDto userAcceptanceRequestDto) throws UserMappingNotFound {
		Optional<User> fromUser = userRepository.findById(userAcceptanceRequestDto.getFromUserId());
		Optional<User> toUser = userRepository.findById(userAcceptanceRequestDto.getToUserId());
		Optional<UserInterest> userMapping = userInterestRepository.findByFromUserAndToUser(toUser.get(),
				fromUser.get());
		if (!userMapping.isPresent()) {
			throw new UserMappingNotFound(Constant.USER_MAPPING_NOT_FOUND);
		} else {
			if (userAcceptanceRequestDto.getStatusCode().equals(Constant.ACCEPTED_CODE)) {

				userMapping.get().setStatus(Constant.ACCEPTED);
				userInterestRepository.save(userMapping.get());
				UserInterest acceptedUserMapping = new UserInterest();
				acceptedUserMapping
						.setFromUser(userRepository.findById(userAcceptanceRequestDto.getFromUserId()).get());
				acceptedUserMapping.setToUser(userRepository.findById(userAcceptanceRequestDto.getToUserId()).get());
				acceptedUserMapping.setStatus(Constant.ACCEPTED);
				userInterestRepository.save(acceptedUserMapping);
				return Constant.ACCEPTED;
			} else {
				userMapping.get().setStatus(Constant.REJECTED);
				userInterestRepository.save(userMapping.get());
				return Constant.REJECTED;
			}
		}
	}

}
