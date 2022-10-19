package com.data.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.data.dashboard.model.Login;


@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{
	@Query(value="select * from login where user_name=?1 AND password=?2",nativeQuery = true)
	Login findByUserNamePassword(String userName,String password);
	
	@Query(value="select * from login where user_name=?1",nativeQuery = true)
	Login findByUserName(String userName);
	
	@Query(value="select * from login where token=?1",nativeQuery = true)
	Login findByToken(String token);
}
