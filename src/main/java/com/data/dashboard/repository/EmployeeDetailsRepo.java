package com.data.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.dashboard.model.EmployeeDetails;

public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Integer>{
	EmployeeDetails findByemployeeId(String id);
	
	
}
