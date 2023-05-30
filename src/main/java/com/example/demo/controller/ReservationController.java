package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	private ReservationService service;
	

	@RequestMapping("reservation/all")
	public Iterable<Reservation> findAll(){
		return service.findAll();
	}

	@RequestMapping(value="reservation/make", method = RequestMethod.POST)
	public Reservation create(@RequestBody ArrayList<Integer> listId) {
		long bookId = listId.get(0);
		long employeeId = listId.get(1);
		
		return service.makeReservation(bookId, employeeId);
	}
	
}
