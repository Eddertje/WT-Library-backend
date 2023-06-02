package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;

import com.example.demo.repository.IBookRepository;

@Service
public class BookService {
	
	@Autowired
	private IBookRepository repo;

	public Optional<Book> findById(long id) {
		return repo.findById(id);
	}

	/**
	 * Searches for books based on the provided search term.
	 *
	 * @param searchTerm the term to search for in book titles, writers, and ISBN.
	 * @return a list of books matching the search criteria.
	 */
	public List<Book> searchBooks(String searchTerm) {
	    Set<Book> books = new HashSet<>();

	    books.addAll(repo.findByTitleContainingIgnoreCase(searchTerm));
	    books.addAll(repo.findByWriterContainingIgnoreCase(searchTerm));
	    books.addAll(repo.findByIsbn(searchTerm));
	    books.addAll(repo.findByKeywords_KeywordContainingIgnoreCase(searchTerm));

	    return new ArrayList<>(books);
	}

	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Book createBook(Book newBook) {
		// TODO Auto-generated method stub
		return repo.save(newBook);
	}
	
	public ArrayList<Book> createBooks(ArrayList<Book> booklist) {
		ArrayList<Book> oklist = new ArrayList<Book>();
		for(Book book : booklist) {
			oklist.add(repo.save(book));
		}
		return oklist;
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		repo.save(book);
	}

}
