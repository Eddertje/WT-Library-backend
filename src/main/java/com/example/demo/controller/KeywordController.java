package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SaveKeywordDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Keyword;
import com.example.demo.service.BookService;
import com.example.demo.service.KeywordService;

@RestController
@CrossOrigin(maxAge=3600)
public class KeywordController {

	@Autowired
    private KeywordService service;
	
	@Autowired
    private BookService bookService;

    /**
     * Handles the addition of a new keyword.
     *
     * @param dto The request body DTO containing the keyword value and the bookId
     * @return The created keyword.
     */
	@RequestMapping(value = "keywords/make", method = RequestMethod.POST)
	public Keyword addKeyword(@RequestBody SaveKeywordDto dto) {
	    String keywordValue = dto.getKeyword();
	    Long bookId = dto.getBookId();

	    // Check if the keyword or bookId is empty or null
	    if (keywordValue == null || keywordValue.trim().isEmpty()) {
	        throw new IllegalArgumentException("Keyword cannot be empty.");
	    }
	    if (bookId == null) {
	        throw new IllegalArgumentException("Book ID cannot be null.");
	    }

	    // Find the book by ID
	    Optional<Book> bookOptional = bookService.findById(bookId);
	    Book book = bookOptional.orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

	    // Check if the keyword already exists
	    Optional<Keyword> existingKeywordOptional = service.findByKeyword(keywordValue);
	    Keyword keyword;
	    if (existingKeywordOptional.isPresent()) {
	        // Keyword already exists, use the existing keyword
	        keyword = existingKeywordOptional.get();
	    } else {
	        // Keyword does not exist, create a new keyword
	        keyword = new Keyword();
	        keyword.setKeyword(keywordValue);
	        keyword.setBooks(new ArrayList<>()); // Initialize the books list
	    }
	    
	    // Associate the book with the keyword only if it is not already associated
	    List<Book> pairedBooks = keyword.getBooks();
        if (!pairedBooks.contains(book)) {
        	pairedBooks.add(book);
        }

	    return service.addKeyword(keyword);
	}

}