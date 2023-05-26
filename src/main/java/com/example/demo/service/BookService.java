package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;

import com.example.demo.repository.IBookRepository;


@Service
public class BookService {
	
	@Autowired
	private IBookRepository repo;
	
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

}
