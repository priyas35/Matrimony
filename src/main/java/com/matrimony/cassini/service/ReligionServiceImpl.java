package com.matrimony.cassini.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.controller.UserInterestController;
import com.matrimony.cassini.entity.Religion;
import com.matrimony.cassini.repository.ReligionRepository;

@Service
public class ReligionServiceImpl implements ReligionService {
	private static final Logger logger = LoggerFactory.getLogger(UserInterestController.class);
	/**
	 * This will inject all the implementations in the religionRepository
	 */

	@Autowired
	ReligionRepository religionRepository;

	/**
	 * This method is used to get the list of reigion
	 */

	@Override
	public List<Religion> getReligions() {
		logger.info("to get all religions");
		return religionRepository.findAll();
	}

}
