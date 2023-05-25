package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge=3600)
public class TestController {

	
	@Autowired
	private TestService service;
	
	@RequestMapping("test/all")
	public Iterable<Test> findAll(){
		return service.findAll();
	}
	
	
}
