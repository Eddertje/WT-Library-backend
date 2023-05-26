package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;


@RestController
@CrossOrigin(maxAge=3600)
public class BookController {

	@Autowired
	private BookService service;
	
	@RequestMapping("book/all")
	public Iterable<Book> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="book/create", method = RequestMethod.POST)
	public Book create(@RequestBody Book newBook) {
		return service.createBook(newBook);
	}
	
	@RequestMapping(value="book/createAll", method = RequestMethod.POST)
	public ArrayList<Book> create(@RequestBody ArrayList<Book> booklist) {			
		return service.createBooks(booklist);
	}
}




