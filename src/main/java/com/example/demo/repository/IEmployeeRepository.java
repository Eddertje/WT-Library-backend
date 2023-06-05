package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IEmployeeRepository extends CrudRepository<Employee, Long>,
        org.springframework.data.jpa.repository.JpaSpecificationExecutor<Employee> {
    @Query( value = "SELECT * FROM Employee e WHERE e.email = ?1 AND e.password = ?2",
            nativeQuery = true)
    Optional<Employee> login(String email, String password);

    @Query(value = "SELECT * FROM Employee e WHERE e.email = ?1",
            nativeQuery = true)
    Employee cookieValues(String email);
}
