package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Component
@Repository
public interface IEmployeeRepository extends CrudRepository<Employee, Long>,
        org.springframework.data.jpa.repository.JpaSpecificationExecutor<Employee>, JpaRepository<Employee, Long> {
   
	
	@Query( value = "SELECT * FROM Employee e WHERE e.email = ?1 AND e.password = ?2",
            nativeQuery = true)
    Optional<Employee> login(String email, String password);

	Employee findEmployeeByEmail(String username);

}
