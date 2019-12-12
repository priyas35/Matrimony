package com.matrimony.cassini.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimony.cassini.controller.UserInterestController;
import com.matrimony.cassini.entity.Occupation;
import com.matrimony.cassini.repository.OccupationRepository;

@Service
public class OccupationServiceImpl implements OccupationService {
	private static final Logger logger = LoggerFactory.getLogger(UserInterestController.class);
	/**
	 * This will inject all the implementations in occupationRepository
	 */

	@Autowired
	OccupationRepository occupationRepository;

	/**
	 * This API is used to get the list of occupations
	 */

	@Override
	public List<Occupation> getOccupations() {
		logger.info("to get all occupations");
		return occupationRepository.findAll();
	}

}
