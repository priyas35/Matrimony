package com.matrimony.cassini.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.service.UserInterestService;

@RestController
@RequestMapping("/interest")
@CrossOrigin
public class UserInteresetController {
	private static final Logger logger = LoggerFactory.getLogger(UserInteresetController.class);
	@Autowired
	UserInterestService userInterestService;
	
	@GetMapping("/{userId}/accepted")
	public ResponseEntity<List<User>> acceptedDetails(@PathVariable("userId") Integer userId) {
		logger.info("Entering into authenticateCustomer in controller");
		return ResponseEntity.ok().body(userInterestService.acceptedDetails(userId));
		
	}

}
