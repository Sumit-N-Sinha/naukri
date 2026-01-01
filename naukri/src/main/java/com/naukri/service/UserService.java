package com.naukri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naukri.entity.User;
import com.naukri.repository.UserRepository;
import com.naukri.securityConfig.SecurityConfig;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encodePassword;

	public User create(User user) {
		String password = user.getPassword();
		System.out.println("Called");
		user.setPassword(encodePassword.encode(password));
		user.setEnabled(true);
		return userRepo.save(user);
	}

}