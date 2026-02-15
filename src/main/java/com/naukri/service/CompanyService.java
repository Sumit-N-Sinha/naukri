package com.naukri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.naukri.dto.CompanyDTO;
import com.naukri.entity.JobApplication;
import com.naukri.entity.User;
import com.naukri.middleware.JwtUtil;
import com.naukri.repository.CompanyRepository;
import com.naukri.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PersistenceContext
	private EntityManager em;

	public List<JobApplication> getAllJobs() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    String name = userRepository.findByEmail(email).get().getName();
	    
		return companyRepository.findByUserName(name);
	}

	public JobApplication create(CompanyDTO obj) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User userRef = new User();
	    try {
	    int userId = userRepository.findByEmail(name).get().getId();
	    System.out.println("name"+userId);
	    userRef = em.getReference(User.class, userId);
	    }catch(Exception e) {
	    	System.out.println(e);
	    }
	    JobApplication jobApplication = new JobApplication();
	    jobApplication.setRole(obj.getRole());
	    jobApplication.setCompany(obj.getCompany());
	    jobApplication.setAppliedDate(obj.getAppliedDate());
	    jobApplication.setUser(userRef);
	    jobApplication.setStatus(obj.getStatus());
	    companyRepository.save(jobApplication);
		return jobApplication;
	}

	
}
