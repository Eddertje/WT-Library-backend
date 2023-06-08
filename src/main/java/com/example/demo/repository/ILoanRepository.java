package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Loan;

public interface ILoanRepository extends CrudRepository<Loan, Long> {

	/**
	 * A method that returns the list of loans for a specified employee
	 * 
	 * @param employeeId the id of the employee for which you want the loans
	 * @return the list of loans attached to this employee
	 */
	Iterable<Loan> findByEmployee_id(long employeeId);

}
