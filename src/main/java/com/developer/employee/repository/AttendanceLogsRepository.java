package com.developer.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.employee.model.AttendanceLogs;

@Repository
public interface AttendanceLogsRepository extends JpaRepository<AttendanceLogs, Long> {
	
	@Query(value = "SELECT * FROM harshlogistics.attendance_logs where username = :username and islogged_in =1", nativeQuery = true)
	public AttendanceLogs findLoggedInUserByUsername(@Param("username") String username);

}


