package com.example.demo.entity;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.demo.security.Authority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * A class that represents an employee/user. It has relations with Reservation and Copy
 * Notable properties beside normal fields include: 
 * 	active: which is false if the employee is archivd (and nullified/normalised) 
 * 	admin: which is true in case the employee is an admin in the system
 * 
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String email;

    @Column()
    private String password;

    private boolean active;

    private boolean admin;
    
    @OneToMany(mappedBy = "employee")
	@JsonIgnore
    private List<Reservation> reservations;
    
    @OneToMany(mappedBy = "employee")
	@JsonIgnore
    private List<Loan> loans;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    //@JoinTable(
    //        name = "EMPLOYEE_AUTHORITY",
    //        joinColumns = {@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")},
    //        inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities = new ArrayList<>();
    
    
    public Employee(){}

    public Employee(long id, String firstName, String lastName, String email, String password, boolean active, boolean admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.admin = admin;
    }

	public long getEmployeeId() {
		return id;
	}

	public void setEmployeeId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", admin=" + admin +
                '}';
    }


	public List<Authority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	
	public void setAuthorities(List<Authority> authorities) {
		// TODO Auto-generated method stub
		this.authorities = authorities;
	}
	
}
