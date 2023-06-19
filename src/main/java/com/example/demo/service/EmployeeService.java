package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Employee;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.security.Authority;
import com.example.demo.security.AuthorityName;
import com.example.demo.security.AuthorityRepository;





/**
 * The service layer for the entity Employee 
 *
 */
@Service
public class EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
    private IEmployeeRepository repo;
	
	@Autowired
	private AuthorityRepository authRepo;
    
	/**
	 * Searches for books based on the provided search term.
	 *
	 * @param searchTerm the term to search for in book titles, writers, and ISBN.
	 * @return a list of books matching the search criteria.
	 */
	public List<Employee> searchEmployees(String searchTerm) {
		return repo.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate firstNameCompare = criteriaBuilder.like(root.get("firstName"), "%" + searchTerm + "%");
                Predicate lastNameCompare = criteriaBuilder.like(root.get("lastName"), "%" + searchTerm + "%");
                Predicate emailCompare = criteriaBuilder.like(root.get("email"), "%" + searchTerm + "%");

                return criteriaBuilder.or(firstNameCompare, lastNameCompare, emailCompare);
            }
        });
    }
	
	public Iterable<Employee> searchEmployees() {
		return repo.findAll();
    }


	/**
	 * creates new employee and necessary roles
	 * @param newEmployee the new employee that needs to be added
	 * @return the new employee
	 */
    public Employee newEmployee(Employee newEmployee) {
        newEmployee.setPassword(this.passwordEncoder.encode(newEmployee.getPassword()));
        
        //setting authorities (user and according to admin property also admin
        Authority authorityU = this.authRepo.findByName(AuthorityName.USER);
        newEmployee.getAuthorities().add(authorityU);
        if (newEmployee.isAdmin()) {
        	Authority authorityA = this.authRepo.findByName(AuthorityName.ADMIN);
            newEmployee.getAuthorities().add(authorityA);
        }
        return repo.save(newEmployee);
    }

    public Employee login(Employee login) {
        Optional<Employee> collection = repo.login(login.getEmail(), String.valueOf(login.getPassword().hashCode()));
        if(collection.isPresent()) {
            return collection.get();
        }
        return null;
    }

	public Optional<Employee> findById(long employeeId) {
		return repo.findById(employeeId);
	}
	
	public Employee findByEmail(String email) {
		return repo.findEmployeeByEmail(email);
	}

    public void makeAdmin(long id) {
        Optional<Employee> update = repo.findById(id);
        if(update.isPresent()) {
            Employee employeeUpdate = update.get();
            employeeUpdate.setAdmin(true);
            repo.save(employeeUpdate);
        }
    }

    public void makeInactive(long id) {
        Optional<Employee> update = repo.findById(id);
        if(update.isPresent()) {
            Employee employeeUpdate = update.get();
            Employee inActive = new Employee();
            inActive.setEmployeeId(employeeUpdate.getEmployeeId());
            repo.save(inActive);
        }
    }

    public void changeFirstName(Employee newEmployee) {
        Optional<Employee> update = repo.findById(newEmployee.getEmployeeId());
        if(update.isPresent()) {
            Employee employeeUpdate = update.get();
            employeeUpdate.setFirstName(newEmployee.getFirstName());
            repo.save(employeeUpdate);
        }
    }

    /**
     * Changes values of user if included in newEmployee
     * @param newEmployee values to be changed
     */
    public void changeValues(Employee newEmployee) {
        Employee employee = repo.findById(newEmployee.getEmployeeId()).get();
        if(newEmployee.getFirstName() != null) {
            employee.setFirstName(newEmployee.getFirstName());
        }
        if(newEmployee.getLastName() != null) {
            employee.setLastName(newEmployee.getLastName());
        }
        if(newEmployee.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
            System.out.println("hi");
        }
        repo.save(employee);
    }
}
