package com.data.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.dashboard.model.BookedServices;


@Repository
public interface BookedServicesRepo extends JpaRepository<BookedServices, Integer>{

	@Query(value= "select * from booked_services ORDER BY date desc",nativeQuery = true)
	List<BookedServices> findSortedDesc();
	
	@Query(value= "select * from booked_services where status != 'completed' ORDER BY date desc limit 5",nativeQuery = true)
	List<BookedServices> findSortedDesclimit5();
	
	@Query(value= "select * from booked_services where mapped_emp = 1? order by date asc",nativeQuery = true)
	List<BookedServices> findDataByMappedEmployee(String empName);
	
	List<BookedServices> findBymappedEmp(String empName);
	
	@Query(value= "select * from booked_services where mapped_emp = :empName and status != 'completed'   ORDER BY date desc limit 5",nativeQuery = true)
	List<BookedServices> findBymappedEmpLimit(@Param("empName") String empName);
	
	@Query(value= "select * from booked_services where status='pending'",nativeQuery = true)
	List<BookedServices> getPendingStatus();
	
	@Query(value= "select * from booked_services where status='pending' and mapped_emp = :empName",nativeQuery = true)
	List<BookedServices> getPendingStatusByEmp(@Param("empName") String empName);
	
	@Query(value= "select * from booked_services where status='completed'",nativeQuery = true)
	List<BookedServices> getCompleteStatus();
	
	@Query(value= "select * from booked_services where status='completed' and mapped_emp = :empName",nativeQuery = true)
	List<BookedServices> getCompleteStatusByEmp(@Param("empName") String empName);
	
	
}
