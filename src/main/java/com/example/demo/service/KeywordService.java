package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Keyword;
import com.example.demo.repository.IKeywordRepository;

@Service
public class KeywordService {

	@Autowired
    private IKeywordRepository repo;

    public Keyword addKeyword(Keyword keyword) {
        return repo.save(keyword);
    }
    
    public Optional<Keyword> findByKeyword(String keyword) {
        return repo.findByKeyword(keyword);
    }
}