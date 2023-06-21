package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * A class that represents a Review. It has relations with Book and Employee.
 * Notable properties beside usual fields include: 
 * 	rating (a score between 1 and 5)
 *  comment (optional comment that a user can leave)
 */
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JsonIgnore
	private Book book;
	
	@ManyToOne(optional = false)
	@JsonIgnore
	private Employee employee;
	
	@Column(nullable = false)
	private int rating;
	
	@Column(length = 9000)
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}