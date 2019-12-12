package com.matrimony.cassini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.repository.UserIntrestRepository;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserInterestServiceImpl implements UserInterestService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserIntrestRepository userIntrestRepository;

	@Override
	public List<User> getAllFilteredUsers(InterestRequestDto interestRequestDto) {
		Optional<User> user = userRepository.findById(interestRequestDto.getUserId());
		if(user.isPresent()) {
		List<User> users = userRepository.findByGenderNot(user.get().getGender());
		users = users.stream().filter(user1 -> user1.getOccupation().equals(interestRequestDto.getOccupation())
				&& user1.getReligion().equals(interestRequestDto.getReligion())).collect(Collectors.toList());
		return users;
		}else {
			return new ArrayList<>();
		}
	}

}
