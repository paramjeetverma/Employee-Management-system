package com.developer.employee.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttendanceLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	private String username;

	private Instant loginTime;

	private Instant logoutTime;

	private String loginLocation;

	private String logoutLocation;

	private Boolean isloggedIn;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Instant getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Instant loginTime) {
		this.loginTime = loginTime;
	}

	public Instant getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Instant logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginLocation() {
		return loginLocation;
	}

	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}

	public String getLogoutLocation() {
		return logoutLocation;
	}

	public void setLogoutLocation(String logoutLocation) {
		this.logoutLocation = logoutLocation;
	}

	public Boolean getIsloggedIn() {
		return isloggedIn;
	}

	public void setIsloggedIn(Boolean isloggedIn) {
		this.isloggedIn = isloggedIn;
	}

}