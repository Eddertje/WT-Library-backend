package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
	 * Searches employees based on the given search term.
	 * 
	 * @param searchTerm the term to search for
	 * @return an iterable collection of employees matching the search criteria
	 */
	@RequestMapping("employees/search")
	public Iterable<Employee> searchEmployees(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
	    return employeeService.searchEmployees(searchTerm);
	}
    
    @RequestMapping(value = "employee/register", method = RequestMethod.POST)
    public Employee create(@RequestBody Employee newEmployee) {
        return employeeService.newEmployee(newEmployee);
    }

    @RequestMapping(value = "employee/login", method = RequestMethod.POST)
    public Employee login(@RequestBody Employee login) {
        return employeeService.login(login);
    }

    @RequestMapping("employee/makeAdmin")
    public void makeAdmin(@RequestParam(value = "id") String id) {
        employeeService.makeAdmin(Long.parseLong(id));
    }

    @RequestMapping("employee/makeInactive")
    public void makeInactive(@RequestParam(value = "id") String id) {
        employeeService.makeInactive(Long.parseLong(id));
    }

    @RequestMapping(value = "employee/changeFirstName", method = RequestMethod.POST)
    public void changeFirstName(@RequestBody Employee newEmployee) {
       employeeService.changeFirstName(newEmployee);
    }

    @RequestMapping(value = "employee/changeLastName", method = RequestMethod.POST)
    public void changeLastName(@RequestBody Employee newEmployee) {
        employeeService.changeLastName(newEmployee);
    }

    @RequestMapping(value = "employee/changeEmail", method = RequestMethod.POST)
    public void changeEmail(@RequestBody Employee newEmployee) {
        employeeService.changeEmail(newEmployee);
    }

    @RequestMapping(value = "employee/changePassword", method = RequestMethod.POST)
    public void changePassword(@RequestBody Employee newEmployee) {
        employeeService.changePassword(newEmployee);
    }

}
