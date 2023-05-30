package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Book;


public interface IBookRepository extends CrudRepository<Book, Long>{

	/**
     * Finds books whose title contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in book titles
     * @return an iterable collection of books matching the search criteria
     */
    Iterable<Book> findByTitleContainingIgnoreCase(String searchTerm);

    /**
     * Finds books whose ISBN contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in book ISBNs
     * @return an iterable collection of books matching the search criteria
     */
    Iterable<Book> findByIsbn(Long searchTerm);

    /**
     * Finds books whose writer's name contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in writer names
     * @return an iterable collection of books matching the search criteria
     */
    Iterable<Book> findByWriterContainingIgnoreCase(String searchTerm);

    /**
     * Finds books whose keywords contain the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in book keywords
     * @return an iterable collection of books matching the search criteria
     */
    Iterable<Book> findByKeywords_KeywordContainingIgnoreCase(String searchTerm);

    /**
     * Searches books based on the specified search term.
     * The search term is matched against book titles, writer names, ISBNs, and keywords (case-insensitive).
     *
     * @param searchTerm the term to search for in book titles, writer names, ISBNs, and keywords
     * @return an iterable collection of books matching the search criteria
     */
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN b.keywords bk WHERE lower(b.title) LIKE %:searchTerm% OR lower(b.writer) LIKE %:searchTerm% OR CAST(b.isbn AS string) LIKE %:searchTerm% OR lower(bk.keyword) LIKE %:searchTerm%")
    Iterable<Book> searchBooks(@Param("searchTerm") String searchTerm);

}