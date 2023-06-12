package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoanEmployeeCopyDto;
import com.example.demo.entity.Loan;

import com.example.demo.repository.ILoanRepository;

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
	 * Searches for loans based on a search term and returns a list of matching LoanEmployeeCopyDto objects.
	 * @param searchTerm The search term to match against employee names, book titles, and ISBNs.
	 * @return A list of LoanEmployeeCopyDto objects that match the search criteria.
	 */
    public List<LoanEmployeeCopyDto> searchLoans(String searchTerm) {
        List<Loan> loans = (List<Loan>) repo.findAllByOrderByReturnDateAscLoanDateDesc();

        // Filter the loans based on the search criteria
        if (searchTerm != null) {
            String keyword = searchTerm.toLowerCase();
            loans = loans.stream()
                    .filter(loan -> containsKeyword(loan, keyword))
                    .collect(Collectors.toList());
        }

        // Map the loans to LoanEmployeeCopyDto objects
        List<LoanEmployeeCopyDto> loanDtos = loans.stream()
                .map(loan -> new LoanEmployeeCopyDto(loan))
                .collect(Collectors.toList());

        return loanDtos;
    }

    /**
     * Checks if a loan contains the specified keyword in employee names, book titles, ISBNs, or loan dates.
     * @param loan The loan to check.
     * @param keyword The keyword to search for.
     * @return True if the loan contains the keyword, otherwise false.
     */
    private boolean containsKeyword(Loan loan, String keyword) {
        LoanEmployeeCopyDto dto = new LoanEmployeeCopyDto(loan);
        return dto.getEmployeeFirstName().contains(keyword) ||
               dto.getEmployeeLastName().contains(keyword) ||
               dto.getBookTitle().contains(keyword) ||
               dto.getIsbn().contains(keyword) ||
               dto.getLoanDate().toString().contains(keyword) ||
               dto.getLoanDate().toString().contains(keyword);
    }

    /**
     * Retrieves a list of loans for a specific employee ordered by return date in ascending order and loan date in ascending order.
     * @param loan The loan containing the employee ID for which to retrieve the loans.
     * @return A list of LoanEmployeeCopyDto objects representing the loans of the specified employee.
     */
	public List<LoanEmployeeCopyDto> findByEmployeeId(Loan loan) {
	    List<Loan> loans = repo.findByEmployee_idOrderByReturnDateAscLoanDateAsc(loan.getId());
	    return loans.stream()
	            .map(LoanEmployeeCopyDto::new)
	            .collect(Collectors.toList());
	}

	public LoanEmployeeCopyDto getLoanByIdDto(long loanID) {
		LoanEmployeeCopyDto newDto= new LoanEmployeeCopyDto(repo.findById(loanID).get());
		return newDto;
	}

}
