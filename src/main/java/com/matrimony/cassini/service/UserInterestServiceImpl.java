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
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
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

		Optional<User> user = userRepository.findById(userId);

		String Status = Constant.STATUS;
		List<UserInterest> uiserMappings = userInterestRepository.findByFromUserAndStatus(user.get(), Status);
		List<User> users = new ArrayList<>();
		for (UserInterest UserMapping : uiserMappings) {
			users.add(UserMapping.getToUser());
		}
		return users;
	}

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

}
