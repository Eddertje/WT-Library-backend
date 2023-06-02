package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reservation;
import com.example.demo.repository.IReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private IReservationRepository repo;
	
	
	public Iterable<Reservation> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Reservation save(Reservation r) {
		return repo.save(r);
	}
	
}
