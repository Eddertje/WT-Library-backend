package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Reservation;

public interface IReservationRepository extends CrudRepository<Reservation, Long>,
	org.springframework.data.jpa.repository.JpaSpecificationExecutor<Reservation> {
}
