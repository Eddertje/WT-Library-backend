package com.example.demo.controller;


import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for all the endpoints regarding the Employee Enitty
 *
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
	 * method/endpoint that searches employees based on the given search term.
	 * 
	 * @param searchTerm the term to search for
	 * @return an iterable collection of employees matching the search criteria
	 */
	@RequestMapping("admin/employees/search")
	public Iterable<Employee> searchEmployees(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
	    return employeeService.searchEmployees(searchTerm);
	}
	
	@RequestMapping("admin/employees/search2")
	public Iterable<Employee> searchEmployees() {
	    return employeeService.searchEmployees();
	}
    
    /**
	 * method/endpoint that creates/registers an employee based on the input given
	 * 
	 * @param newEmployee is the new object of the employee with all its values within
	 * @return the employee that was just created
	 */
    @RequestMapping(value = "admin/employee/register", method = RequestMethod.POST)
    public Employee create(@RequestBody Employee newEmployee) {
        return employeeService.newEmployee(newEmployee);
    }

    /**
	 * method/endpoint that serves as a login based on password and email
	 * 
	 * @param login is the employee object through which the email and passwords are given/received
	 * @return the employee found in the database based on the password and email
	 */
    @RequestMapping(value = "employee/login", method = RequestMethod.POST)
    public Employee login(@RequestBody Employee login) {
        return employeeService.login(login);
    }

	/**
	 * method/endpoint that serves as a getter
	 *
	 * @param user is the employee object through which the email is given/received
	 * @return the employee found in the database based on the email
	 */
	@RequestMapping(value = "employee/get", method = RequestMethod.POST)
	public Employee get(@RequestBody Employee user) {
		return employeeService.get(user);
	}

    /**
	 * method/endpoint that changes the selected user (through ID) to an admin
	 * 
	 * @param the id (as a string) for the employee that is selectd
	 */
    @RequestMapping("employee/makeAdmin")
    public void makeAdmin(@RequestParam(value = "id") String id) {
        employeeService.makeAdmin(Long.parseLong(id));
    }

    /**
	 * method/endpoint that changes the selected user (through ID) to inactive
	 * 
	 * @param the id (as a string) for the employee that is selected
	 */
    @RequestMapping("employee/makeInactive")
    public void makeInactive(@RequestParam(value = "id") String id) {
        employeeService.makeInactive(Long.parseLong(id));
    }

    /**
	 * method/endpoint that changes the first name of the employee (through ID)
	 * 
	 * @param newEmployee is the employee that is used in which the new first name resides
	 */
    @RequestMapping(value = "employee/changeFirstName", method = RequestMethod.POST)
    public void changeFirstName(@RequestBody Employee newEmployee) {
       employeeService.changeFirstName(newEmployee);
    }

    /**
	 * method/endpoint that changes the last name of the employee (through ID)
	 * 
	 * @param newEmployee is the employee that is used in which the new last name resides
	 */
    @RequestMapping(value = "employee/changeLastName", method = RequestMethod.POST)
    public void changeLastName(@RequestBody Employee newEmployee) {
        employeeService.changeLastName(newEmployee);
    }

    /**
	 * method/endpoint that changes the email of the employee (through ID)
	 * 
	 * @param newEmployee is the email that is used in which the new email resides
	 */
    @RequestMapping(value = "employee/changeEmail", method = RequestMethod.POST)
    public void changeEmail(@RequestBody Employee newEmployee) {
        employeeService.changeEmail(newEmployee);
    }

    /**
	 * method/endpoint that changes the first password of the employee
	 * 
	 * @param newEmployee is the employee that is used in which the new password resides
	 */
    @RequestMapping(value = "employee/changePassword", method = RequestMethod.POST)
    public void changePassword(@RequestBody Employee newEmployee) {
        employeeService.changePassword(newEmployee);
    }

}
