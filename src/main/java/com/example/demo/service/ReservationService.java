package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reservation;
import com.example.demo.repository.IReservationRepository;

import jakarta.transaction.Transactional;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class ReservationService {
	
	@Autowired
	private IReservationRepository repo;
		
	/**
	 * Searches for books based on the provided search term.
	 *
	 * @param searchTerm the term to search for in book titles, writers, and ISBN and the date.
	 * @return a list of books matching the search criteria.
	 */
	public List<Reservation> searchReservations(String searchTerm) {
		return repo.findAll(new Specification<Reservation>() {
            @Override
            public Predicate toPredicate(Root<Reservation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate firstNameCompare = criteriaBuilder.like(root.get("employee").get("firstName"), "%" + searchTerm + "%");
                Predicate lastNameCompare = criteriaBuilder.like(root.get("employee").get("lastName"), "%" + searchTerm + "%");
                Predicate titleCompare = criteriaBuilder.like(root.get("book").get("title"), "%" + searchTerm + "%");
                Predicate dateCompare = criteriaBuilder.like(root.get("reservationDate"), searchTerm);

                return criteriaBuilder.or(firstNameCompare, lastNameCompare, titleCompare, dateCompare);
            }
        });
    }
	
	@Transactional
    public void deleteById(long id) {
        repo.deleteById(id);
    }
	
	public Iterable<Reservation> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Optional<Reservation> findById(long id) {
	    return repo.findById(id);
	}
	
	public Reservation save(Reservation r) {
		return repo.save(r);
	}

	/**
	 * service method that requests the repo for the reservations of a employee based on the employee_ID within reservation
	 * @param res the reservation in which the needed employee_ID is stored (only this employee_ID is needed within reservation)
	 * @return returns iterable list of reservations based on the employeeID
	 */
	public Iterable<Reservation> findByEmployeeId(Reservation res) {
		return repo.findByEmployee_id(res.getId());
	}

	public void deleteReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		repo.deleteById(reservation.getId());
	}
	
}
