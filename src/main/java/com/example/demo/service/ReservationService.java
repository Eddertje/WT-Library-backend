package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reservation;
import com.example.demo.repository.IReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private IReservationRepository repo;

	public Reservation makeReservation(Reservation newReservation) {
		return repo.save(newReservation);
	}
	
	public Iterable<Reservation> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
}
