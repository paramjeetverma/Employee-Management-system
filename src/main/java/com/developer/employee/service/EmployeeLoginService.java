package com.developer.employee.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.developer.employee.config.JwtTokenUtil;
import com.developer.employee.model.AttendanceLogs;
import com.developer.employee.model.EmployeeLoginDetails;
import com.developer.employee.model.LoginLogs;
import com.developer.employee.repository.AttendanceLogsRepository;
import com.developer.employee.repository.LoginLogRepository;
import com.developer.employee.utils.Utils;

@Service
public class EmployeeLoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private Utils utils;

	@Autowired
	private LoginLogRepository loginLogRepository;

	@Autowired
	private AttendanceLogsRepository attendanceLogsRepository;

	@Value("${lockTime}")
	private int lockTime;

	public void checkAttempts(String username, String password, String location) throws Exception {
		LoginLogs loginLogs = new LoginLogs();
		loginLogs.setUsername(username);

		AttendanceLogs attendanceLogs = new AttendanceLogs();
		attendanceLogs.setUsername(username);

		LoginLogs existingLoginLogs = loginLogRepository.findLoginLogByUsername(username);

		if (existingLoginLogs != null) {
			if (!existingLoginLogs.getIsloggedIn()) {
				if (existingLoginLogs.getLoginAttempts() < 3) {
					try {
						System.out.println(
								"authenticating user for " + (existingLoginLogs.getLoginAttempts() + 1) + " time");
						authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
						System.out.println("authentication successfull");
						existingLoginLogs.setLoginAttempts(0);
						existingLoginLogs.setLockTime(null);
						existingLoginLogs.setLoginTime(Instant.now());
						existingLoginLogs.setIsloggedIn(true);
						existingLoginLogs.setLoginLocation(location);
						attendanceLogs.setLoginTime(Instant.now());
						attendanceLogs.setIsloggedIn(true);
						attendanceLogs.setLoginLocation(location);
						attendanceLogsRepository.save(attendanceLogs);
					} catch (DisabledException e) {
						throw new Exception("USER_DISABLED", e);
					} catch (BadCredentialsException e) {
						System.out.println(
								"authentication failed " + (existingLoginLogs.getLoginAttempts() + 1) + " time");
						existingLoginLogs.setLoginAttempts(existingLoginLogs.getLoginAttempts() + 1);

						throw new Exception("INVALID_CREDENTIALS", e);
					} finally {

						loginLogRepository.save(existingLoginLogs);

					}
				} else {
					if (existingLoginLogs.getLockTime() != null) {
						System.out.println("lock time comparison--->");
						System.out.println(existingLoginLogs.getLockTime().compareTo(Instant.now()) > 0);
						System.out.println("in not null loop");
						if (existingLoginLogs.getLockTime().compareTo(Instant.now()) < 0) {
							try {
								System.out.println("authentication after locktime complete");
								authenticationManager
										.authenticate(new UsernamePasswordAuthenticationToken(username, password));
								System.out.println("authentication successfull");
								existingLoginLogs.setLoginTime(Instant.now());
								existingLoginLogs.setLoginAttempts(0);
								existingLoginLogs.setLockTime(null);
								existingLoginLogs.setIsloggedIn(true);
								existingLoginLogs.setLoginLocation(location);
								attendanceLogs.setLoginLocation(location);
								attendanceLogs.setLoginTime(Instant.now());
								attendanceLogs.setIsloggedIn(true);
								attendanceLogsRepository.save(attendanceLogs);
							} catch (DisabledException e) {
								throw new Exception("USER_DISABLED", e);
							} catch (BadCredentialsException e) {
								System.out.println("authentication failed after locktime complete");
								existingLoginLogs.setLoginAttempts(1);
								existingLoginLogs.setLockTime(null);
								throw new Exception("INVALID_CREDENTIALS", e);
							} finally {
								loginLogRepository.save(existingLoginLogs);
							}
						} else {
							System.out.println("locktime still not complete");
							throw new Exception("Try again after 15 Minutes");
						}
					} else {
						System.out.println("login attempts exceeded more than 3 times locking first time");
						System.out.println("locktime is :: " + lockTime);
						existingLoginLogs.setLockTime(Instant.now().plus(lockTime, ChronoUnit.MINUTES));
						loginLogRepository.save(existingLoginLogs);
						throw new Exception("Try again after 15 Minutes");
					}
				}
			} else {
				throw new Exception("Logout first");
			}
		} else {
			try {
				System.out.println("first time authentication");
				loginLogs.setLoginTime(Instant.now());
				loginLogs.setIsloggedIn(true);
				loginLogs.setLoginLocation(location);
				attendanceLogs.setLoginTime(Instant.now());
				attendanceLogs.setIsloggedIn(true);
				attendanceLogs.setLoginLocation(location);
				attendanceLogsRepository.save(attendanceLogs);
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			} catch (DisabledException e) {
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				loginLogs.setLoginAttempts(1);
				System.out.println("authentication failed 1st time");
				throw new Exception("INVALID_CREDENTIALS", e);
			} finally {
				loginLogRepository.save(loginLogs);
			}
		}
	}

	public String generateJwtToken(EmployeeLoginDetails employeeLoginDetails, String location) throws Exception {

		checkAttempts(employeeLoginDetails.getUsername(), employeeLoginDetails.getPassword(), location);

		final UserDetails userDetails = utils.loadUserByUsername(employeeLoginDetails.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		LoginLogs existingLoginLogs = loginLogRepository.findLoginLogByUsername(employeeLoginDetails.getUsername());

		if (existingLoginLogs != null) {
			existingLoginLogs.setJwtToken(token);
			loginLogRepository.save(existingLoginLogs);
		}

		return token;

	}

	public void logout(String token, String location) throws Exception {
		System.out.println("token -->" + token);
		System.out.println(token != null);
		if (token != null) {
			String jwtToken = token.substring(7);
			String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			LoginLogs existingLoginLogs = loginLogRepository.findLoginLogByUsername(username);
			AttendanceLogs attendanceLogs = attendanceLogsRepository.findLoggedInUserByUsername(username);
			if (existingLoginLogs != null) {
				if (attendanceLogs != null && existingLoginLogs.getIsloggedIn() && attendanceLogs.getIsloggedIn()) {
					existingLoginLogs.setLogoutTime(Instant.now());
					existingLoginLogs.setIsloggedIn(false);
					existingLoginLogs.setLogoutLocation(location);
					loginLogRepository.save(existingLoginLogs);
					attendanceLogs.setLogoutTime(Instant.now());
					attendanceLogs.setIsloggedIn(false);
					attendanceLogs.setLogoutLocation(location);
					attendanceLogsRepository.save(attendanceLogs);
				} else {
					throw new Exception("Login first");
				}

			} else {
				throw new Exception("Invalid username");
			}
		} else {
			throw new Exception("Invalid token");
		}

	}

}