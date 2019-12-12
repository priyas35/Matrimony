package com.matrimony.cassini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.entity.UserIntrest;
import com.matrimony.cassini.repository.UserIntrestRepository;
import com.matrimony.cassini.repository.UserRepository;

@Service
public class UserIntrestServiceImpl implements UserIntrestService {
	
	@Autowired
	UserIntrestRepository userIntrestRepository;
	
	@Autowired
	UserRepository userRepository;

	
	@Override
	public List<User> acceptedDetails(Integer userId) {
		
		Optional<User> user = userRepository.findById(userId);

		String Status = "Approved";
		List<UserIntrest> uiserMappings = userIntrestRepository.findByFromUserAndStatus(user.get(), Status);
		List<User> users = new ArrayList<>();
		for (UserIntrest UserMapping : uiserMappings) {
			users.add(UserMapping.getToUser());
		}
		return users;
	}

}
