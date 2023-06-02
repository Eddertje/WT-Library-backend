package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Book;


public interface IBookRepository extends CrudRepository<Book, Long>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<Book> {

	/**
     * Finds books whose title contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in book titles
     * @return an iterable collection of books matching the search criteria
     */
    List<Book> findByTitleContainingIgnoreCase(String searchTerm);

    /**
     * Finds books whose ISBN contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in book ISBNs
     * @return an iterable collection of books matching the search criteria
     */
    List<Book> findByIsbn(String searchTerm);

    /**
     * Finds books whose writer's name contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in writer names
     * @return an iterable collection of books matching the search criteria
     */
    List<Book> findByWriterContainingIgnoreCase(String searchTerm);

    /**
     * Finds books whose keywords contain the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in book keywords
     * @return an iterable collection of books matching the search criteria
     */
    List<Book> findByKeywords_KeywordContainingIgnoreCase(String searchTerm);
}