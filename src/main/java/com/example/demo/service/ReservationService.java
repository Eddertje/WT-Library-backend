package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.IBookRepository;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.repository.IReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private IReservationRepository repo;
	
	@Autowired
	private IBookRepository bookRepo;
	
	@Autowired
	private IEmployeeRepository employeeRepo;

	
	public Reservation makeReservation(Long bookId, Long employeeId) {	
		Book book = bookRepo.findById(bookId).orElseThrow();
		Employee employee = employeeRepo.findById(employeeId).orElseThrow();
		
		Reservation newReservation = new Reservation();
		
//		newReservation.setBookId(book);
//		newReservation.setEmployeeId(employee);
		newReservation.setReservationDate(LocalDate.now());
		newReservation.setAllowed(false);
		
		return repo.save(newReservation);
	}
	
	
	
	public Iterable<Reservation> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Reservation save(Reservation r) {
		return repo.save(r);
	}
	
}
