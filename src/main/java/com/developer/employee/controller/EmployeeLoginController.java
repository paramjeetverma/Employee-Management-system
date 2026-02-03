package com.developer.employee.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.developer.employee.model.EmployeeLoginDetails;
import com.developer.employee.model.JwtResponse;
import com.developer.employee.service.EmployeeLoginService;

@RestController
@CrossOrigin("*")
public class EmployeeLoginController {

	@Autowired
	private EmployeeLoginService employeeLoginService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(HttpServletRequest httpServletRequest,@RequestBody EmployeeLoginDetails employeeLoginDetails) {

		try {
			return new ResponseEntity<>(new JwtResponse(employeeLoginService.generateJwtToken(employeeLoginDetails,httpServletRequest.getHeader("location"))),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/log-out")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		Map<String, Object> response = new HashMap<>();
		try {		
			employeeLoginService.logout(request.getHeader("Authorization"),request.getHeader("location"));
			response.put("Status", "Successfully Logged Out");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			response.put("Status", e.getLocalizedMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
