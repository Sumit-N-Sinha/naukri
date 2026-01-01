package com.naukri.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

	public Boolean login(String name, String password) {
		Optional<User> userDet = userRepo.findByName(name);
		if(userDet.isPresent()) {
			User currUser = userDet.get();
			if(encodePassword.matches(password, currUser.getPassword())) {
				return true;
			}
			return false;
		}else {
			return false;
		}
	}

	public User loadUserByUsername(String username) {
		return userRepo.findByName(username).get();
	}

}