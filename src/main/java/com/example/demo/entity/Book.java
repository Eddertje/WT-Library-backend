package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A class that represents a Book (type, not token). It has relations with Copy, Reservation and Keyword.
 * Notable properties beside usual fields include: 
 * 	stock (the amount of Copies of a book), 
 * 	available (true if a copy is available for loan), 
 * 	active (false if book is archived)
 * 
 *
 */
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private String isbn;
	
	@Column(nullable = false, length = 255)
	private String title;
	
	@Column(nullable = false, length = 50)
	private String writer;
	
	@Column(nullable = false, length = 255)
	private String photo;
	
	@Column(nullable = false)
	private boolean available;

	@Column(nullable = false)
	private int stock;
	
	@Column()
	private double avgScore;
	
	@Column(nullable = false)
	private boolean active;

	@ManyToMany(mappedBy = "books")
	@JsonIgnore
    private List<Keyword> keywords;

	@OneToMany(mappedBy = "book")
	@JsonIgnore
    private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "book")
    private List<Review> reviews;
	
	@OneToMany(mappedBy = "book")
    private List<Copy> copies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Copy> getCopies() {
		return copies;
	}

	public void setCopies(List<Copy> copies) {
		this.copies = copies;
	}
}
