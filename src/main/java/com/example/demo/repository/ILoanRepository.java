package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Loan;

public interface ILoanRepository extends CrudRepository<Loan, Long>,
org.springframework.data.jpa.repository.JpaSpecificationExecutor<Loan> {

	/**
	 * A method that returns the list of loans for a specified employee
	 * 
	 * @param employeeId the id of the employee for which you want the loans
	 * @return the list of loans attached to this employee
	 */
	List<Loan> findByEmployee_idOrderByReturnDateAscLoanDateAsc(long employeeId);

	List<Loan> findAllByOrderByReturnDateAscLoanDateDesc();


}
