package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Keyword;

import com.example.demo.repository.IBookRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

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
		return repo.findAll(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate titleCompare = criteriaBuilder.like(root.get("title"), "%" + searchTerm + "%");
                Predicate writerCompare = criteriaBuilder.like(root.get("writer"), "%" + searchTerm + "%");
                Predicate isbnCompare = criteriaBuilder.equal(root.get("isbn"), "%" + searchTerm + "%");

                //TODO: Martijn ging hier nog naar kijken, de Join zou automatisch moeten gebeuren
//                CriteriaQuery<Keyword> cq = criteriaBuilder.createQuery(Keyword.class);
//                Root<Keyword> keywords = cq.from(Keyword.class);
//                Predicate keywordsCompare = criteriaBuilder.like(keywords.get("keyword"), "%" + searchTerm + "%");

                CriteriaQuery<Book> cq = criteriaBuilder.createQuery(Book.class);
                Root<Book> books = cq.from(Book.class);
                Join<Book, Keyword> join = books.join("keywords");
                Predicate keywordCompare = criteriaBuilder.like(join.get("keyword"), "%" + searchTerm + "%");

                cq.select(books).where(keywordCompare);

                return criteriaBuilder.or(titleCompare, writerCompare, isbnCompare);//, keywordsCompare);
            }
        });
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

}
