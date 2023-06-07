package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Loan;
import com.example.demo.entity.Reservation;

public interface ILoanRepository extends CrudRepository<Loan, Long>,
org.springframework.data.jpa.repository.JpaSpecificationExecutor<Loan> {
	
}
