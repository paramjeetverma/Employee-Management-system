package com.developer.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.employee.model.SalaryDetails;
import com.developer.employee.service.SalaryDetailsService;

@RestController
public class SalaryDetailsController {

	@Autowired
	private SalaryDetailsService salaryDetailsService;

	@PostMapping(value = "/addSalaryDetails")
	public ResponseEntity<?> addSalaryDetails(@RequestBody SalaryDetails salaryDetails) {

		return new ResponseEntity<>(salaryDetailsService.saveSalaryDetails(salaryDetails), HttpStatus.CREATED);
	}
	
}
