package com.naukri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naukri.dto.CompanyDTO;
import com.naukri.entity.JobApplication;

@Repository
public interface CompanyRepository extends JpaRepository<JobApplication, Long> {

	List<JobApplication> findByUserName(String name);

	JobApplication save(CompanyDTO obj);

}
