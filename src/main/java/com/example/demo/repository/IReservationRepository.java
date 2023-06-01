package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Reservation;

public interface IReservationRepository extends CrudRepository<Reservation, Long> {

}
