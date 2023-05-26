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
	
	/**
	 * Searches books based on the given search term and an (optional) search field.
	 * 
	 * @param searchTerm the term to search for
	 * @param searchField the field to search in (title, author, isbn, keyword)
	 * @return an iterable collection of books matching the search criteria
	 */
	@RequestMapping("books/search")
	public Iterable<Book> searchBooks(
	        @RequestParam(value = "searchTerm", required = false) String searchTerm,
	        @RequestParam(value = "searchField", required = false) String searchField
	) {
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
