package com.example.demo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservationId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Book bookId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id", referencedColumnName = "employeeId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee employeeId;
	
	@Column(nullable = false)
	private LocalDate reservationDate;
	
	@Column(nullable = false)
	private boolean reviewed;
	
	@Column(nullable = false)
	private boolean allowed;
	
	public Reservation() {
		
	}

	public Reservation(int reservationId, Book bookId, Employee employeeId, LocalDate reservationDate, boolean reviewed,
			boolean allowed) {
		this.reservationId = reservationId;
		this.bookId = bookId;
		this.employeeId = employeeId;
		this.reservationDate = reservationDate;
		this.reviewed = reviewed;
		this.allowed = allowed;
	}

	public int getId() {
		return reservationId;
	}

	public void setId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Book getBookId() {
		return bookId;
	}

	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	
	
}
