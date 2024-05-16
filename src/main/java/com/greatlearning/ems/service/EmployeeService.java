package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployeeService {

    List<Employee> list();

    public void save(Employee employee);

    public Employee findById(Long employeeID);

    public void deleteById(Long employeeId);

    List<Employee> findEmployeeByFirstName(String firstName);

    List<Employee> findEmployeesSortedByFirstName(Sort.Direction order);
}
