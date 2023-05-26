package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Book;


public interface IBookRepository extends CrudRepository<Book, Long>{

	Iterable<Book> findByTitleContainingIgnoreCase(String searchTerm);

	Iterable<Book> findByIsbnContainingIgnoreCase(String searchTerm);

	Iterable<Book> findByWriterContainingIgnoreCase(String searchTerm);

	Iterable<Book> findByKeywords_KeywordContainingIgnoreCase(String searchTerm);
	
	@Query("SELECT b FROM Book b WHERE lower(b.title) LIKE %:searchTerm% OR lower(b.writer) LIKE %:searchTerm% OR b.isbn = :searchTerm OR EXISTS (SELECT bk FROM Book b JOIN b.keywords bk WHERE lower(bk.keyword) LIKE %:searchTerm%)")
    Iterable<Book> searchBooks(@Param("searchTerm") String searchTerm);

}