package com.matrimony.cassini.service;

import java.util.List;

import com.matrimony.cassini.dto.FilterRequestDto;
import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.dto.InterestResponseDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.exception.UserNotFoundException;

public interface UserInterestService {
	
	List<User> getAllFilteredUsers(FilterRequestDto filterRequestDto);
	List<User> acceptedDetails(Integer userId);
 
	InterestResponseDto showInterest(InterestRequestDto interestRequestDto)throws UserNotFoundException;

}
