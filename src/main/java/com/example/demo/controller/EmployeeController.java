package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "employee/register", method = RequestMethod.POST)
    public Employee create(@RequestBody Employee newEmployee) {
        return employeeService.newEmployee(newEmployee);
    }

    @RequestMapping(value = "employee/login", method = RequestMethod.POST)
    public boolean login(@RequestBody Employee login) {
        return employeeService.login(login);
    }
}
