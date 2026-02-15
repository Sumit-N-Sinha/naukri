package com.naukri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naukri.dto.CompanyDTO;
import com.naukri.entity.JobApplication;
import com.naukri.entity.User;
import com.naukri.service.CompanyService;

@RestController
@RequestMapping("/api/job")
@CrossOrigin("*")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/all")
	public List<JobApplication> getAllJobs(){
		return companyService.getAllJobs();
	}
	
	@PostMapping("/addJob")
	public ResponseEntity<?> addNewJob(@RequestBody CompanyDTO obj){
		try {
			JobApplication newUser = this.companyService.create(obj);
			return ResponseEntity.ok(newUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

}
