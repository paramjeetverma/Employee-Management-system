package com.developer.employee.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.developer.employee.model.Employee;
import com.developer.employee.model.EmployeeLoginDetails;
import com.developer.employee.repository.EmployeeLoginDetailsRepository;
import com.developer.employee.repository.EmployeeRespository;

@Service
public class EmployeeLoginDetailsService {

	@Autowired
	private EmployeeLoginDetailsRepository employeeLoginDetailsRepository;

	@Autowired
	private EmployeeRespository employeeRespository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public EmployeeLoginDetails saveLoginDetails(EmployeeLoginDetails employeeLoginDetails) throws Exception {

		Optional<Employee> value = employeeRespository.findById(employeeLoginDetails.getEmpId());

		if (value.isPresent()) {
			employeeLoginDetails.setPassword(bcryptEncoder.encode(employeeLoginDetails.getPassword()));
			return employeeLoginDetailsRepository.save(employeeLoginDetails);
		} else {
			throw new Exception("Employee with doesn't exist with employeeId ::" + employeeLoginDetails.getEmpId());
		}

	}
	
//	@Scheduled(cron = "0 */1 * * * *")
//	public void performTaskUsingCron() {
//
//		System.out.println("Regular task performed using Cron at "
//				+ Instant.now());
//
//	}

}
