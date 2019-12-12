package com.matrimony.cassini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.constants.Constant;
import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserInterest;
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
			users = users.stream().filter(user1 -> user1.getOccupation().equals(filterRequestDto.getOccupation())
					&& user1.getReligion().equals(filterRequestDto.getReligion())).collect(Collectors.toList());
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
}
