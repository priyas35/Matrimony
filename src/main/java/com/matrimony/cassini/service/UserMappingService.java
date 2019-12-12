package com.matrimony.cassini.service;

import java.util.List;

import com.matrimony.cassini.dto.GetUsersRequestDto;
import com.matrimony.cassini.entity.User;

public interface UserMappingService {
	
	List<User> getAllFilteredUsers(GetUsersRequestDto getUsersRequestDto);

}
