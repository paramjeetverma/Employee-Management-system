package com.developer.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.employee.model.EmployeeLoginDetails;

@Repository
public interface EmployeeLoginDetailsRepository extends JpaRepository<EmployeeLoginDetails, Long> {

	@Query(value = "SELECT * FROM harshlogistics.employee_login_details where username = :username", nativeQuery = true)
	public EmployeeLoginDetails getLoginDetailsByUsername(@Param("username") String username);
}
