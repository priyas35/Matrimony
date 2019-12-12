package com.matrimony.cassini.service;

import java.util.List;

import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.entity.User;

public interface UserInterestService {
	
	List<User> getAllFilteredUsers(InterestRequestDto interestRequestDto);
	List<User> acceptedDetails(Integer userId);

}
