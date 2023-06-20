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
	
}