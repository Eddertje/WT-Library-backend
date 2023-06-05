package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Loan;

public interface ILoanRepository extends CrudRepository<Loan, Long> {

}
