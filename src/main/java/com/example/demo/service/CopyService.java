package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Copy;
import com.example.demo.repository.ICopyRepository;

@Service
public class CopyService {
	
	@Autowired
	private ICopyRepository repo;
	
	public Iterable<Copy> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public Optional<Copy> findById(long id) {
		return repo.findById(id);
	}
	
	public Copy createCopy(Copy newCopy) {
		// TODO Auto-generated method stub
		return repo.save(newCopy);
	}

	public void updateCopy(Copy copy) {
		// TODO Auto-generated method stub
		repo.save(copy);
	}

}
