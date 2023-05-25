package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Test;
import com.example.demo.repository.ITestRepository;



@Service
public class TestService {

	@Autowired
	private ITestRepository repo;
	
	public Iterable<Test> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
