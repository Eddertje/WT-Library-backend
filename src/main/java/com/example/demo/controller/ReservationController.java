package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SaveReservationDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Reservation;
import com.example.demo.service.BookService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ReservationService;

@CrossOrigin(maxAge = 3600)
@RestController
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Searches employees based on the given search term.
	 * 
	 * @param searchTerm the term to search for
	 * @return an iterable collection of employees matching the search criteria
	 */
	@RequestMapping("reservation/search")
	public Iterable<Reservation> searchReservations(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
	    return service.searchReservations(searchTerm);
	}
	
	@RequestMapping("reservation/all")
	public Iterable<Reservation> findAll(){
		return service.findAll();
	}

	@RequestMapping(value="reservation/make", method = RequestMethod.POST)
	public Reservation create(@RequestBody SaveReservationDto dto) {
		// Book vinden
		Optional<Book> bookOptional = bookService.findById(dto.getBookId());

		// Employee vinden
		Optional<Employee> employeeOptional = employeeService.findById(dto.getEmployeeId()); 

		Reservation reservation = new Reservation();
		
		reservation.setBook(bookOptional.get());
		reservation.setEmployee(employeeOptional.get());
		reservation.setReservationDate(LocalDate.now());
		reservation.setAllowed(false);

		return service.save(reservation);
	}
	
	@RequestMapping(value = "reservation/update", method = RequestMethod.PUT)
	public Reservation updateReservation(@RequestBody Reservation reservation) {
	    Optional<Reservation> optionalReservation = service.findById(reservation.getId());

	    Reservation existingReservation = optionalReservation.orElseThrow(() -> new IllegalArgumentException("Invalid Reservation ID"));
	    existingReservation.setAllowed(reservation.isAllowed());

	    return service.save(existingReservation);
	}
	
}
