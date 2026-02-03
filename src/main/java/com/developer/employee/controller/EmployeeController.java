package com.developer.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.employee.model.Employee;
import com.developer.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/api/createEmployee")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {

		return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);

	}

	@GetMapping("/getAllEmployee")
	public ResponseEntity<?> getAllEmployee() {

		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);

	}

	@GetMapping("/getHighestSalary/{noOfEmployee}")
	public ResponseEntity<?> getHighestSalary(@PathVariable("noOfEmployee") long noOfEmployee) {

		return new ResponseEntity<>(employeeService.getHighestSalary(noOfEmployee), HttpStatus.OK);

	}

	@PostMapping("/updateEmployee")
	public ResponseEntity<?> updateEmployeeDetails(@RequestBody Employee employee) {

		try {
			return new ResponseEntity<>(employeeService.updateEmployeeDetails(employee), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
