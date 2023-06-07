package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SaveReservationDto;
import com.example.demo.entity.Copy;
import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Loan;
import com.example.demo.entity.Reservation;
import com.example.demo.service.BookService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LoanService;
import com.example.demo.service.ReservationService;

@RestController
@CrossOrigin(maxAge = 3600)
public class LoanController {

	@Autowired
	private LoanService service;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ReservationService reservationService;


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

	@RequestMapping(value = "loan/make", method = RequestMethod.POST)
	public Loan createFromReservation(@RequestBody SaveReservationDto dto) {
	    Optional<Book> optionalBook = bookService.findById(dto.getBookId());
	    Optional<Employee> optionalEmployee = employeeService.findById(dto.getEmployeeId());

	    // Check if Book and Employee exist
	    Book book = optionalBook.orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));
	    Employee employee = optionalEmployee.orElseThrow(() -> new IllegalArgumentException("Invalid Employee ID"));

	    // Check if the book has available copies
	    List<Copy> availableCopies = book.getCopies().stream()
	            .filter(copy -> copy.isActive() && copy.getLoans().stream().allMatch(loan -> loan.getReturnDate() != null))
	            .collect(Collectors.toList());

	    if (availableCopies.isEmpty()) {
	        throw new IllegalStateException("No available copies of the book");
	    }

	    // If this was the last available copy, set isAvailable to false
	    if (availableCopies.size() == 1) {
	        book.setAvailable(false);
	        bookService.updateBook(book);
	    }   
	    Copy selectedCopy = availableCopies.get(0);
	    Loan loan = new Loan();
	    loan.setEmployee(employee);
	    loan.setCopy(selectedCopy);
	    loan.setLoanDate(LocalDate.now());

	    // Delete the reservation
	    reservationService.deleteByBookAndEmployee(book, employee);
	    
	    return service.save(loan);
	}
	
}
