package com.developer.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalaryDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long salaryId;
	
	private long empId;
	
	private long monthlySalary;
	
	private long annualSalary;

	public long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(long salaryId) {
		this.salaryId = salaryId;
	}


	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public long getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(long monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public long getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(long annualSalary) {
		this.annualSalary = annualSalary;
	}
	
	

}
