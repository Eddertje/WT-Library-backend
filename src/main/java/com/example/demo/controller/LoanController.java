package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoanReservationDto;
import com.example.demo.dto.SaveLoanDto;
import com.example.demo.entity.Copy;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Loan;
import com.example.demo.service.CopyService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LoanService;
import com.example.demo.service.ReservationService;

@RestController
@CrossOrigin(maxAge = 3600)
public class LoanController {

	@Autowired
	private LoanService service;
	
	@Autowired
	private CopyService copyService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ReservationService reservationService;

	@RequestMapping(value = "loan/complete/{loanId}", method = RequestMethod.PUT)
	public Loan completeLoan(@PathVariable Long loanId) {
		Loan updatedLoan = service.updateLoanReturnDate(loanId);
		return updatedLoan;
	}

	@RequestMapping("loan/all")
	public Iterable<Loan> findAll(){
		return service.findAll();
	}
	
	/**
	 * Searches loan for specified employee
	 * 
	 * @param a loan in the requestbody in which only the employeeID field is necessary (all other fields can be undefined/empty)
	 * @return an iterable collection of loans matching the employee ID received from the loan
	 */
	@RequestMapping(value ="loan/user", method= RequestMethod.POST)
	public Iterable<Loan> findByEmployeeId(@RequestBody Loan loan){
		return service.findByEmployeeId(loan);
	}

	/**
	 * Creates a new loan based on the provided data.
	 *
	 * @param dto The data to create the loan from (copyId and employeeId)
	 * @return The created loan
	 * @throws IllegalArgumentException If the copyId or employeeId is invalid
	 */
	@RequestMapping(value = "loan/make", method = RequestMethod.POST)
	public Loan create(@RequestBody SaveLoanDto dto) {
	    Optional<Copy> optionalCopy = copyService.findById(dto.getCopyId());
	    Optional<Employee> optionalEmployee = employeeService.findById(dto.getEmployeeId());

	    // Check if Copy and Employee exist
	    Copy copy = optionalCopy.orElseThrow(() -> new IllegalArgumentException("Invalid Copy ID"));
	    Employee employee = optionalEmployee.orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));

	    Loan loan = new Loan()	;
	    loan.setEmployee(employee);
	    loan.setCopy(copy);
	    loan.setLoanDate(LocalDate.now());
	    
	    return service.save(loan);
	}
	
	/**
	 * Creates a new loan from a reservation based on the provided data.
	 *
	 * @param dto The data to create the loan from (bookId and employeeId) and a reservation id
	 * @return The created loan
	 * @throws IllegalArgumentException If the bookId or employeeId is invalid
	 * @throws IllegalStateException    If there are no available copies of the book
	 */
	@RequestMapping(value = "loan/makeFromReservation", method = RequestMethod.POST)
	public Loan createFromReservation(@RequestBody LoanReservationDto dto) {
		
		SaveLoanDto loanDto = dto.getLoanDto();
	    long reservationId = dto.getReservationId();
	    System.out.println(reservationId);
	    
		Optional<Copy> optionalCopy = copyService.findById(loanDto.getCopyId());
	    Optional<Employee> optionalEmployee = employeeService.findById(loanDto.getEmployeeId());

	    // Check if Copy and Employee exist
	    Copy copy = optionalCopy.orElseThrow(() -> new IllegalArgumentException("Invalid Copy ID"));
	    Employee employee = optionalEmployee.orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));

	    Loan loan = new Loan()	;
	    loan.setEmployee(employee);
	    loan.setCopy(copy);
	    loan.setLoanDate(LocalDate.now());

	    // Delete the reservation
	    reservationService.deleteById(reservationId);
	    
	    return service.save(loan);
	}
	
	/**
	 * Searches loans based on the given search term.
	 * 
	 * @param searchTerm the term to search for
	 * @return an iterable collection of loans matching the search criteria
	 */
	@RequestMapping("loans/search")
	public Iterable<Loan> searchLoans(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
	    return service.searchLoans(searchTerm);
	}
	
}
