package com.example.demo.security;


import java.io.Serializable;
import java.util.List;


import com.example.demo.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    @JsonManagedReference
    private List<Employee> employees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<Employee> getUsers() {
		return employees;
	}

	public void setUsers(List<Employee> employees) {
		this.employees = employees;
	} 
	
    @Override
	public String toString() {
		return "Authority [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}
}