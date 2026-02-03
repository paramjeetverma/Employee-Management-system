package com.developer.employee.service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.employee.model.Employee;
import com.developer.employee.repository.EmployeeRespository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRespository employeeRespository;

	public List<Employee> getAllEmployees() {

		return employeeRespository.findAll();

	}

	public Employee createEmployee(Employee employee) {
		employee.setCreatedOn(Instant.now());
		employee.setCreatedBy("Admin");
		employee.setUpdatedBy("Admin");
		employee.setUpdatedOn(Instant.now());
		return employeeRespository.save(employee);

	}

	public List<Map<String, Object>> getHighestSalary(long noOfEmployee) {
		return employeeRespository.getHighestSalary(noOfEmployee);

	}

	public Employee updateEmployeeDetails(Employee employee) throws Exception {

		Optional<Employee> value = employeeRespository.findById(employee.getEmployeeId());

		if (value.isPresent()) {

			Employee savedEmployee = value.get();
			employee.setCreatedBy(savedEmployee.getCreatedBy());
			employee.setCreatedOn(savedEmployee.getCreatedOn());
			employee.setEmployeeId(savedEmployee.getEmployeeId());
			employee.setUpdatedBy("Admin");
			employee.setUpdatedOn(Instant.now());
			return employeeRespository.save(employee);
		} else {
			throw new Exception("Employee not found with Employee Id ::" + employee.getEmployeeId());
		}

	}

}
