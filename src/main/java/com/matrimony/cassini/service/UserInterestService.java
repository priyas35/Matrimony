package com.matrimony.cassini.service;

import java.util.List;

import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.entity.User;

public interface UserInterestService {
	
	List<User> getAllFilteredUsers(FilterRequestDto filterRequestDto);
	List<User> acceptedDetails(Integer userId);

}
