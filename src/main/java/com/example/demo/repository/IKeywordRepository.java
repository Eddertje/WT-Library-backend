package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Keyword;

@Repository
public interface IKeywordRepository extends CrudRepository<Keyword, Long> {

	 Optional<Keyword> findByKeyword(String keyword);
}
