package com.data.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.dashboard.model.FinalFeeStructure;

@Repository
public interface FinalFeeStructureRepo extends JpaRepository<FinalFeeStructure, Integer>{
	
	FinalFeeStructure findByurlName(String serv);
	

}
