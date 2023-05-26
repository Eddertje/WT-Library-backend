package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;

import com.example.demo.repository.IBookRepository;


@Service
public class BookService {
	
	@Autowired
	private IBookRepository repo;

    public Iterable<Book> searchBooks(String searchTerm, String searchField) {
        if (searchField != null && !searchField.isEmpty()) {
            switch (searchField) {
                case "title":
                    return repo.findByTitleContainingIgnoreCase(searchTerm);
                case "isbn":
                    return repo.findByIsbnContainingIgnoreCase(searchTerm);
                case "writer":
                    return repo.findByWriterContainingIgnoreCase(searchTerm);
                case "keywords":
                    return repo.findByKeywords_KeywordContainingIgnoreCase(searchTerm);
                default:
                    throw new IllegalArgumentException("Invalid search field: " + searchField);
            }
        } else {
            // Search in all the fields using the default query-based approach if searchField is provided
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
