package com.matrimony.cassini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.dto.GetUsersRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.repository.UserMappingRepository;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserMappingServiceImpl implements UserMappingService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMappingRepository userMappingRepository;

	@Override
	public List<User> getAllFilteredUsers(GetUsersRequestDto getUsersRequestDto) {
		Optional<User> user = userRepository.findById(getUsersRequestDto.getUserId());
		if(user.isPresent()) {
		List<User> users = userRepository.findByGenderNot(user.get().getGender());
		users = users.stream().filter(user1 -> user1.getOccupation().equals(getUsersRequestDto.getOccupation())
				&& user1.getReligion().equals(getUsersRequestDto.getReligion())).collect(Collectors.toList());
		return users;
		}else {
			return new ArrayList<>();
		}
	}

}
