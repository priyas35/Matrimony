package com.matrimony.cassini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.dto.InterestRequestDto;
import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.service.UserInterestService;

@RestController
@RequestMapping("/interest")
@CrossOrigin
public class UserIntersetController {
	
	@Autowired
	UserInterestService userInterestService;
	
	@GetMapping("/{userId}/accepted")
	public ResponseEntity<List<User>> acceptedDetails(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok().body(userInterestService.acceptedDetails(userId));
		
	}
	
	@PostMapping
	public ResponseEntity<List<User>> getAllFilteredUsers(@RequestBody InterestRequestDto interestRequestDto){
		return ResponseEntity.ok().body(userInterestService.getAllFilteredUsers(interestRequestDto));
	}

}
