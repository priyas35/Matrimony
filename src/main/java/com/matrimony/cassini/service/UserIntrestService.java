package com.matrimony.cassini.service;

import java.util.List;

import com.matrimony.cassini.entity.User;

public interface UserIntrestService {

	public List<User> acceptedDetails(Integer userId);

}
