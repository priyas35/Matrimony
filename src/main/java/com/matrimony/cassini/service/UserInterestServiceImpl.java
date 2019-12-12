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
import com.matrimony.cassini.exception.UserNotFoundException;
import com.matrimony.cassini.repository.UserInterestRepository;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserInterestServiceImpl implements UserInterestService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserInterestRepository userInterestRepository;

	@Override
	public List<User> getAllFilteredUsers(FilterRequestDto filterRequestDto) {
		Optional<User> user = userRepository.findById(filterRequestDto.getUserId());
		if (user.isPresent()) {
			List<User> users = userRepository.findByGenderNot(user.get().getGender());
			List<UserInterest> userInterests = userInterestRepository.findByToUser(user.get());
			for (UserInterest userInterest : userInterests) {
				users = users.stream()
						.filter(user1 -> !(user1.getUserId().equals(userInterest.getFromUser().getUserId())))
						.collect(Collectors.toList());
			}
			List<UserInterest> userInterests1 = userInterestRepository.findByFromUserAndStatus(user.get(), "Requested");
			for (UserInterest userInterest : userInterests1) {
				users = users.stream()
						.filter(user1 -> !(user1.getUserId().equals(userInterest.getToUser().getUserId())))
						.collect(Collectors.toList());
			}

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

	@Override
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

	@Override
	public List<Optional<User>> requestList(Integer userId) throws RequestNotRaisedException {
		Optional<User> currentuser = userRepository.findById(userId);
		List<UserInterest> userInterests = userInterestRepository.findAllUserMappingsByToUserAndStatus(currentuser,
				Constant.REQUESTED);
		List<Optional<User>> users = new ArrayList<>();
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

	@Override
	public String userResponse(UserAcceptanceRequestDto userAcceptanceRequestDto)
			throws RequestNotRaisedException, UserNotFoundException {
		Optional<User> fromUser = userRepository.findById(userAcceptanceRequestDto.getFromUserId());
		Optional<User> toUser = userRepository.findById(userAcceptanceRequestDto.getToUserId());
		if (!toUser.isPresent() && fromUser.isPresent()) {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		}else {
		Optional<UserInterest> userInterests = userInterestRepository.findByFromUserAndToUser(toUser.get(),
				fromUser.get());
		
		if (!userInterests.isPresent()) {
			throw new RequestNotRaisedException(Constant.REQUEST_NOT_RAISED);
		} else {
			if (userAcceptanceRequestDto.getStatusCode().equals(Constant.ACCEPTED_CODE)) {

				userInterests.get().setStatus(Constant.ACCEPTED);
				userInterestRepository.save(userInterests.get());
				UserInterest acceptedUserMapping = new UserInterest();
				acceptedUserMapping
						.setFromUser(userRepository.findById(userAcceptanceRequestDto.getFromUserId()).get());
				acceptedUserMapping.setToUser(userRepository.findById(userAcceptanceRequestDto.getToUserId()).get());
				acceptedUserMapping.setStatus(Constant.ACCEPTED);
				userInterestRepository.save(acceptedUserMapping);
				return Constant.ACCEPTED;
			} else {
				userInterests.get().setStatus(Constant.REJECTED);
				userInterestRepository.save(userInterests.get());
				return Constant.REJECTED;
			}
		}

		}
	}

}
