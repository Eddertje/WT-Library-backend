package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TestService {

	@Autowired
	private ITestRepository repo;
	
	public Iterable<Test> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
