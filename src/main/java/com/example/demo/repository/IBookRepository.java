package com.example.demo.repository;

import java.util.List;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Book;

public interface IBookRepository extends CrudRepository<Book, Long>, org.springframework.data.jpa.repository.JpaSpecificationExecutor<Book> {

    /**
     * Querry that finds all books and also returns a reservation if a specific user has one.
     * @return
     */
    @Query(value = """
            SELECT b.id, b.title, b.writer, b.isbn, r.id
            FROM book b LEFT OUTER JOIN reservation r ON b.id = r.book_id AND r.employee_id = ?1
            WHERE b.title LIKE %?2% OR b.writer LIKE %?2% OR b.isbn LIKE %?2%
            """, nativeQuery = true
    )
    List<Tuple> findBooksAndReservations(long id, String searchTerm);

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