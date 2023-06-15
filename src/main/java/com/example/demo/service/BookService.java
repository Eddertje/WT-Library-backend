package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.dto.FindBookDto;
import com.example.demo.dto.GetBookDto;
import jakarta.persistence.Tuple;
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
	
	/**
	 * Method that createds multiple books by iterating over the list.
	 * @param booklist arraylist of books
	 * @return the list of newly created books
	 */
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

	/**
	 * Prepares the output of findBooksAndReservations into a DTO
	 * @param getBookDto
	 * @return A list of books + reservation tied to those
	 */
	public Iterable<FindBookDto> searchBooksAndReservation(GetBookDto getBookDto) {
		List<Tuple> findBookTuples = repo.findBooksAndReservations(getBookDto.getId(), getBookDto.getSearchTerm());

		List<FindBookDto> findBookDtos = findBookTuples.stream()
				.map(t -> new FindBookDto(
						extract(0, t),
						t.get(1, String.class),
						t.get(2, String.class),
						t.get(3, String.class),
						extract(4, t)
				))
				.collect(Collectors.toList());

		return findBookDtos;
	}

	/**
	 * Casts a known long type to long
	 * @return
	 */
	public long extract(int i, Tuple t) {
		if (t.get(i) == null) {
			return -1;
		}
		return (long) t.get(i);
	}
}
