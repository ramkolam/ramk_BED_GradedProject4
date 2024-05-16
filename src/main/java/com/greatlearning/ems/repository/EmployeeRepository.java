package com.greatlearning.ems.repository;

import com.greatlearning.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository 
	extends JpaRepository<Employee, Long>{

}