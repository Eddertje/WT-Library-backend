package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Copy;

public interface ICopyRepository extends CrudRepository<Copy, Long> {
    /**
     * Finds copies whose book id contains the specified search term, ignoring case.
     *
     * @param searchTerm the term to search for in copies bookId
     * @return an iterable collection of copies matching the search criteria
     */
    List<Copy> findByBookId(long bookId);
    
    /**
     * Finds copies linked to the specified book id and where active = true
     *
     * @param searchTerm the term to search for in copies bookId
     * @return an iterable collection of copies matching the search criteria
     */
    List<Copy> findAllByBookIdAndActive(long bookId, boolean active);
   
}