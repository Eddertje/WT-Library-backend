package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;

import com.example.demo.repository.IBookRepository;


@Service
public class BookService {
	
	@Autowired
	private IBookRepository repo;

    /**
     * Searches books based on the specified search term and search field.
     * If the search field is not provided, it searches in all fields using the default query-based approach.
     *
     * @param searchTerm the term to search for
     * @param searchField the field to search in (e.g., title, isbn, writer, keywords)
     * @return an iterable collection of books matching the search criteria
     * @throws IllegalArgumentException if an invalid search field is provided
     */
    public Iterable<Book> searchBooks(String searchTerm, String searchField) {
        if (searchField != null && !searchField.isEmpty()) {
            switch (searchField) {
                case "title":
                    return repo.findByTitleContainingIgnoreCase(searchTerm);
                case "isbn":
                	try {
                        return repo.findByIsbnContaining(Long.parseLong(searchTerm));
                    } catch (NumberFormatException e) {
                        return repo.searchBooks(searchTerm.toLowerCase());
                    }
                case "writer":
                    return repo.findByWriterContainingIgnoreCase(searchTerm);
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
