package com.naukri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naukri.entity.LoginBody;
import com.naukri.entity.User;
import com.naukri.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService useservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		try {
			System.out.println("POST User payload ID: " + user.getId());
			User newUser = this.useservice.create(user);
			return ResponseEntity.ok(newUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginBody loginBody){
		try {
			Boolean bool = this.useservice.login(loginBody.name,loginBody.password);
			System.out.println(bool);
			if(bool) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(201);
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(401);
			}
		}catch( Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

}
