package com.developer.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.employee.model.LoginLogs;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLogs, Long> {

	@Query(value = "SELECT * FROM harshlogistics.login_logs where username = :username", nativeQuery = true)
	public LoginLogs findLoginLogByUsername(@Param("username") String username);
	
//	@Query(value = "SELECT * FROM harshlogistics.login_logs where username = :username and islogged_in =1", nativeQuery = true)
//	public LoginLogs findLoggedInUserByUsername(@Param("username") String username);

}
