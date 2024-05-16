package com.greatlearning.ems.service.impl;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.repository.EmployeeRepository;
import com.greatlearning.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> list() {
        return employeeRepository.findAll();
    }


    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }


    @Override
    public Employee findById(Long employeeID) {
        Optional<Employee> employee = employeeRepository.findById(employeeID);
        return employee.orElse(null);
    }


    @Override
    public void deleteById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<Employee> findEmployeeByFirstName(String firstName) {
        Employee e = new Employee();
        e.setFirstName(firstName);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("firstName",
                ExampleMatcher.GenericPropertyMatchers.ignoreCase()).withIgnorePaths("id", "lastName", "email");
        Example<Employee> example = Example.of(e, exampleMatcher);
        return employeeRepository.findAll(example);
    }

    @Override
    public List<Employee> findEmployeesSortedByFirstName(Sort.Direction order) {
        Employee e = new Employee();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("firstName",
                ExampleMatcher.GenericPropertyMatchers.ignoreCase()).withIgnorePaths("id", "lastName", "email");
        Example<Employee> example = Example.of(e, exampleMatcher);
        return employeeRepository.findAll(example, Sort.by(order, "firstName"));
    }

}