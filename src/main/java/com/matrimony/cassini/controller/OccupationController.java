package com.matrimony.cassini.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.entity.Occupation;
import com.matrimony.cassini.service.OccupationService;

@RestController
@RequestMapping("/occupations")
@CrossOrigin
public class OccupationController {
	
	private static final Logger logger = LoggerFactory.getLogger(OccupationController.class);
	
	/**
	 * This will inject all the implementations in the occupationService
	 */

	@Autowired
	OccupationService occupationService;
	/**
	 * This API is used to get the list of occupations
	 * @return This returns the occupation list
	 */

	@GetMapping
	public ResponseEntity<List<Occupation>> getOccupations() {
		return ResponseEntity.ok().body(occupationService.getOccupations());
	}

}
