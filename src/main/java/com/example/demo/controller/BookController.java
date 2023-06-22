package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.FindBookDto;
import com.example.demo.dto.GetBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * Searches books based on the given search term.
	 * 
	 * @param searchTerm the term to search for
	 * @return an iterable collection of books matching the search criteria
	 */
	@RequestMapping("books/search")
	public Iterable<Book> searchBooks(@RequestParam(value = "searchTerm", required = false) String searchTerm) {
		if (searchTerm == null || searchTerm == "") {
			System.out.println("test");
			return service.findAll();
		} else {
			return service.searchBooks(searchTerm);
		}
	}

	/**
	 * Searches books and reservations based on the given id and search term.
	 *
	 * @return an iterable collection of books matching the search criteria + reservations of the given employee tied to those books
	 */
	@RequestMapping("books/booksReservation")
	public Iterable<FindBookDto> searchBooksAndReservation(@RequestBody GetBookDto getBookDto) {
		return service.searchBooksAndReservation(getBookDto);
	}
	
	/*
	 * method/endpoint for getting all books
	 */
	@RequestMapping("books/all")
	public Iterable<Book> findAll(){
		return service.findAll();
	}
	
	/**
	 * method/endoint for creating a book (POST method)
	 * 
	 * @param accepts Book within the request body with all the necessary fields
	 * @return the just created book 
	 */
	@RequestMapping(value="admin/books/create", method = RequestMethod.POST)
	public Book create(@RequestBody Book newBook) {
		newBook.setActive(true);
		return service.createBook(newBook);
	}
	
	/**
	 * method/endoint for creating multiple books (POST method)
	 * 
	 * @param booklist an arraylist of books to be created
	 * @return an arraylist of just creatd books
	 */
	@RequestMapping(value="admin/book/createAll", method = RequestMethod.POST)
	public ArrayList<Book> create(@RequestBody ArrayList<Book> booklist) {	
		for(Book book : booklist) {
			book.setActive(true);
		}
		return service.createBooks(booklist);
	}
	
	/**
	 * method/endoint getting a book through it's id
	 * 
	 * @param the id of a book
	 * @return the book (if it exists) with the specified id
	 */
	@RequestMapping("book/{id}")
	public Book findBookById(@PathVariable Long id) {
		Optional<Book> optionalBook = service.findById(id);
		Book book = optionalBook.get();
		return book;
	}
	
	/**
	 * the method/endpoint for updating a book based on it's id(PATCH METHOD)
	 * 
	 * @param id the id of the book that needs to be updated
	 * @param updatedBook the 'new' book with updated fields
	 * 
	 */
	@RequestMapping(value="admin/book/update/{id}", method = RequestMethod.PATCH)
	public void update(@PathVariable Long id, @RequestBody Book updatedBook){
		Optional<Book> existingBook = service.findById(id);
		
		if (existingBook.isPresent()) {
			Book book = existingBook.get();
			
			book.setIsbn(updatedBook.getIsbn());
			book.setTitle(updatedBook.getTitle());
			book.setWriter(updatedBook.getWriter());
			book.setPhoto(updatedBook.getPhoto());
			book.setAvailable(updatedBook.isAvailable());
			book.setStock(updatedBook.getStock());
			book.setActive(updatedBook.isActive());
			book.setKeywords(updatedBook.getKeywords());			
			
			service.updateBook(book);
		}
	}
}



