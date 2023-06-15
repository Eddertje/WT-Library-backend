package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Test;
import com.example.demo.service.TestService;

@RestController
@CrossOrigin(maxAge=3600)
public class TestController {

	
	@Autowired
	private TestService service;
	
	@RequestMapping("admin/test/all")
	public Iterable<Test> findAll(){
		return service.findAll();
	}
	
	
}
