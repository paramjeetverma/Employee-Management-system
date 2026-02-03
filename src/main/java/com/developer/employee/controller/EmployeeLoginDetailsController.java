package com.developer.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.employee.model.EmployeeLoginDetails;
import com.developer.employee.service.EmployeeLoginDetailsService;

@RestController
public class EmployeeLoginDetailsController {

	@Autowired
	private EmployeeLoginDetailsService employeeLoginDetailsService;

	@PostMapping(value = "/api/saveLoginDetails")
	public ResponseEntity<?> saveLoginDetails(@RequestBody EmployeeLoginDetails employeeLoginDetails) throws Exception {

		return new ResponseEntity<>(employeeLoginDetailsService.saveLoginDetails(employeeLoginDetails),
				HttpStatus.CREATED);

	}

}
