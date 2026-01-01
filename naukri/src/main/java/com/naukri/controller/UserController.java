package com.naukri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naukri.entity.User;
import com.naukri.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService useservice;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		try {
			User newUser = this.useservice.create(user);
			return ResponseEntity.ok("User saved successfully"+newUser.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

}
