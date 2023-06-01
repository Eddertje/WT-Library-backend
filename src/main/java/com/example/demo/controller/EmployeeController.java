package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "employee/register", method = RequestMethod.POST)
    public Employee create(@RequestBody Employee newEmployee) {
        return employeeService.newEmployee(newEmployee);
    }

    @RequestMapping(value = "employee/login", method = RequestMethod.POST)
    public Employee login(@RequestBody Employee login) {
        return employeeService.login(login);
    }

}
