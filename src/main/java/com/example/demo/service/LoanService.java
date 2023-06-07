package com.example.demo.service;

import java.util.Optional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Copy;
import com.example.demo.entity.Employee;

import com.example.demo.repository.ILoanRepository;

@Service
public class LoanService {
	
	@Autowired
	private ILoanRepository repo;
	
	public Iterable<Loan> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Loan save(Loan l) {
		return repo.save(l);
	}

	/**
	 * service method that requests the repo for the loans of an employee based on the employee_ID within loan
	 * @param loan the loan in which the needed employee_ID is stored (only this employee_ID is needed within loan)
	 * @return returns iterable list of loans based on the employeeID
	 */
	public Iterable<Loan> findByEmployeeId(Loan loan) {
		return repo.findByEmployee_id(loan.getId());
	}

}
