package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Copy;
import com.example.demo.entity.Employee;

import com.example.demo.repository.ILoanRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

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
	 * Updates the return date of a loan to the current local date.
	 *
	 * @param loanId the ID of the loan to update
	 * @return the updated Loan object
	 * @throws RuntimeException if the loan with the specified ID is not found
	 */
	@Transactional
	public Loan updateLoanReturnDate(Long loanId) {
		// Retrieve the loan by ID from the database
		Loan loan = repo.findById(loanId)
		  .orElseThrow(() -> new RuntimeException("Loan not found"));
		
		// Set the returnDate to the current local date
		LocalDate currentDate = LocalDate.now();
		loan.setReturnDate(currentDate);
	
		// Save the updated loan back to the database
	    Loan updatedLoan = repo.save(loan);
	
	    return updatedLoan;
	}
	
	/**
	 * Searches for books based on the provided search term.
	 *
	 * @param searchTerm the term to search for in book titles, writers, and ISBN and the dates.
	 * @return a list of books matching the search criteria.
	 */
	public List<Loan> searchLoans(String searchTerm) {
		return repo.findAll(new Specification<Loan>() {
            @Override
            public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate firstNameCompare = criteriaBuilder.like(root.get("employee").get("firstName"), "%" + searchTerm + "%");
                Predicate lastNameCompare = criteriaBuilder.like(root.get("employee").get("lastName"), "%" + searchTerm + "%");
                Predicate titleCompare = criteriaBuilder.like(root.get("copy").get("book").get("title"), "%" + searchTerm + "%");
                Predicate loanDateCompare = criteriaBuilder.like(root.get("loanDate"), searchTerm);
                Predicate returnDateCompare = criteriaBuilder.like(root.get("returnDate"), searchTerm);

                return criteriaBuilder.or(firstNameCompare, lastNameCompare, titleCompare, loanDateCompare, returnDateCompare);
            }
        });
    }
}
