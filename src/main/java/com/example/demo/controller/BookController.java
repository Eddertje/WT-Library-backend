package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;


@RestController
@CrossOrigin(maxAge=3600)
public class BookController {

	@Autowired
	private BookService service;
	
	@RequestMapping("books/search")
	public Iterable<Book> searchBooks(@RequestParam("searchTerm") String searchTerm, @RequestParam("searchField") String searchField) {
        return service.searchBooks(searchTerm, searchField);
    }
	
	@RequestMapping("books/all")
	public Iterable<Book> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="books/create", method = RequestMethod.POST)
	public Book crate(@RequestBody Book newBook) {
		return service.createBook(newBook);
	}

}
