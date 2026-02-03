package com.developer.employee.utils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.developer.employee.model.EmployeeLoginDetails;
import com.developer.employee.repository.EmployeeLoginDetailsRepository;

@Component
public class Utils implements UserDetailsService {

	@Autowired
	private EmployeeLoginDetailsRepository employeeLoginDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		EmployeeLoginDetails employeeLoginDetails = employeeLoginDetailsRepository.getLoginDetailsByUsername(username);

		if (employeeLoginDetails == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(employeeLoginDetails.getUsername(),
				employeeLoginDetails.getPassword(), new ArrayList<>());
	}

}
