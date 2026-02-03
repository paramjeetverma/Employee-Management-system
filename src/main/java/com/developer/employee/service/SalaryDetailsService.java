package com.developer.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.employee.model.SalaryDetails;
import com.developer.employee.repository.SalaryDetailsRepository;

@Service
public class SalaryDetailsService {

	@Autowired
	private SalaryDetailsRepository salaryDetailsRepository;

	public SalaryDetails saveSalaryDetails(SalaryDetails salaryDetails) {

		return salaryDetailsRepository.save(salaryDetails);
	}
}
