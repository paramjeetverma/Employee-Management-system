package com.developer.employee.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.employee.model.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Long> {

	@Query(value = "select * from harshlogistics.employee_details e\r\n"
			+ "inner join harshlogistics.salary_details s\r\n" + "on e.employee_id = s.emp_Id\r\n"
			+ "order by s.monthly_salary desc limit :noOfEmployee", nativeQuery = true)
	public List<Map<String, Object>> getHighestSalary(@Param("noOfEmployee") long noOfEmployee);

}
