package com.matrimony.cassini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimony.cassini.entity.User;
import com.matrimony.cassini.service.UserIntrestService;

@RestController
@RequestMapping("/intrestedprofiles")
@CrossOrigin
public class UserIntrestController {
	
	@Autowired
	private UserIntrestService userIntrestgService;
	
	@GetMapping("/{userId}/acceptedorrejected")
	public ResponseEntity<List<User>> acceptedDetails(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok().body(userIntrestgService.acceptedDetails(userId));
		
	}

}
