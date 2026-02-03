package com.developer.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developer.employee.model.SalaryDetails;

@Repository
public interface SalaryDetailsRepository extends JpaRepository<SalaryDetails, Long> {

}
