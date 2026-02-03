package com.developer.employee.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoginLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loginLogId;

	private String username;

	private Instant loginTime;

	private Instant logoutTime;

	private String loginLocation;

	private String logoutLocation;

	private int loginAttempts;

	private Instant lockTime;

	private Boolean isloggedIn;
	
	private String jwtToken;

	public long getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(long loginLogId) {
		this.loginLogId = loginLogId;
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

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Instant getLockTime() {
		return lockTime;
	}

	public void setLockTime(Instant lockTime) {
		this.lockTime = lockTime;
	}

	public Boolean getIsloggedIn() {
		return isloggedIn;
	}

	public void setIsloggedIn(Boolean isloggedIn) {
		this.isloggedIn = isloggedIn;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}