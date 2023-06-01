package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.IEmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    IEmployeeRepository repo;

    public Employee newEmployee(Employee newEmployee) {
        newEmployee.setPassword(String.valueOf(newEmployee.getPassword().hashCode()));
        return repo.save(newEmployee);
    }

    public boolean login(Employee login) {
        Optional<Employee> collection = repo.login(login.getEmail(), String.valueOf(login.getPassword().hashCode()));
        return collection.isPresent();
    }

	public Optional<Employee> findById(long employeeId) {
		return repo.findById(employeeId);
	}
}
