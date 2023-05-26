package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;

import com.example.demo.repository.IBookRepository;


@Service
public class BookService {
	
	@Autowired
	private IBookRepository repo;

	/**
	 * Searches books based on the given search term and an (optional) search field.
	 *
	 * @param searchTerm  the term to search for
	 * @param searchField the field to search in (title, author, isbn, keyword)
	 * @return an iterable collection of books matching the search criteria
	 * @throws IllegalArgumentException if an invalid search field is provided
	 */
	public Iterable<Book> searchBooks(String searchTerm, String searchField) {
	    if (searchTerm == null || searchTerm.isEmpty()) {
	        return Collections.emptyList();
	    } else if (searchField != null && !searchField.isEmpty()) {
	        switch (searchField) {
	            case "title":
	                return repo.findByTitleContainingIgnoreCase(searchTerm);
	            case "writer":
	                return repo.findByWriterContainingIgnoreCase(searchTerm);
	            case "isbn":
	                try {
	                    Long isbn = Long.parseLong(searchTerm);
	                    return repo.findByIsbn(isbn);
	                } catch (NumberFormatException e) {
	                    return Collections.emptyList();
	                }
	            case "keywords":
	                return repo.findByKeywords_KeywordContainingIgnoreCase(searchTerm);
	            default:
	                throw new IllegalArgumentException("Invalid search field: " + searchField);
	        }
	    } else {
	        return repo.searchBooks(searchTerm.toLowerCase());
	    }
	}
	
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Book createBook(Book newBook) {
		// TODO Auto-generated method stub
		return repo.save(newBook);
	}

}
