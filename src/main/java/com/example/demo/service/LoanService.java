package com.example.demo.service;

import java.util.Optional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.Copy;
import com.example.demo.entity.Employee;

import com.example.demo.repository.ILoanRepository;

@Service
public class LoanService {
	
	@Autowired
	private ILoanRepository repo;

	public Optional<Loan> findById(long id) {
		return repo.findById(id);
	}

}
