package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SaveReservationDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Reservation;
import com.example.demo.service.BookService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService service;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private EmployeeService employeeService;


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
		
		reservation.setReservationDate(LocalDate.now());
		reservation.setBook(bookOptional.get());
		reservation.setEmployee(employeeOptional.get());

		return service.save(reservation);
	}
	
}
