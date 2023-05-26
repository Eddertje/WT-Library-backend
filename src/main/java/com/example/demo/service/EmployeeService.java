package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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
}
