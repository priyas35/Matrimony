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

import com.matrimony.cassini.entity.Religion;
import com.matrimony.cassini.service.ReligionService;

@RestController
@RequestMapping("/religions")
@CrossOrigin
public class ReligionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReligionController.class);

	/**
	 * This will inject all the implementations in the religionService
	 */

	@Autowired
	ReligionService religionService;

	/**
	 * This API is used to get the list of religion
	 * 
	 * @return This returns the religion list
	 */
	@GetMapping
	public ResponseEntity<List<Religion>> getReligions() {
		return ResponseEntity.ok().body(religionService.getReligions());
	}

}
