package com.developer.employee.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeLoginDetails {

	@Id
	private long empId;

	private String username;

	private String password;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
