package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Copy;
import com.example.demo.entity.Loan;
import com.example.demo.repository.ICopyRepository;

@Service
public class CopyService {
	
	@Autowired
	private ICopyRepository repo;
	
	public Iterable<Copy> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Optional<Copy> findById(long id) {
		return repo.findById(id);
	}
	
	public Copy createCopy(Copy newCopy) {
		// TODO Auto-generated method stub
		return repo.save(newCopy);
	}

	public void updateCopy(Copy copy) {
		// TODO Auto-generated method stub
		repo.save(copy);
	}
	

	/**
	 * Searches for copies based on the provided book id.
	 *
	 * @param searchTerm the term to search for in book titles, writers, and ISBN.
	 * @return a list of books matching the search criteria.
	 */
	public List<Copy> searchCopies(long bookId) {
	    Set<Copy> copies= new HashSet<>();

	    copies.addAll(repo.findByBookId(bookId));
	    
	    return new ArrayList<>(copies);
	}
	
	/**
	 * Searches for copies based on the provided book id where active = true and that are not loaned out yet.
	 *
	 * @return a list of copies matching the search criteria.
	 */
	public Iterable<Copy> getActiveCopiesWithoutLoan(long bookId) {
	    return repo.findAllByBookIdAndActive(bookId, true)
	        .stream()
	        .filter(copy -> {
	            List<Loan> loans = copy.getLoans();
	            // Copy isn't paired with loans, so the copy is active
	            if (loans.isEmpty()) {
	                return true;
	            }
	            for (Loan loan : loans) {
	            	// Copy is paired with a loan without a return date
	                if (loan.getReturnDate() == null) {
	                    return false; 
	                }
	            }
	            // All loans have return dates, so the copy is active
	            return true;
	        })
	        .collect(Collectors.toList());
	}



}
