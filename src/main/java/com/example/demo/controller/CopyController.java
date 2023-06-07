package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.saveCopyDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Copy;
import com.example.demo.service.BookService;
import com.example.demo.service.CopyService;

@RestController
@CrossOrigin(maxAge=3600)
public class CopyController {
	
	@Autowired
	private CopyService service;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("copy/{id}")
	public Copy findCopyById(@PathVariable Long id) {
		Optional<Copy> optionalCopy = service.findById(id);
		Copy copy = optionalCopy.get();
		return copy;
	}
	
	@RequestMapping("copies/all")
	public Iterable<Copy> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value="copy/create", method = RequestMethod.POST)
	public Copy create(@RequestBody saveCopyDto dto) {
		// Find book
		Optional <Book> bookOptional = bookService.findById(dto.getBookId());
		
		Copy copy = new Copy();
		
		copy.setBook(bookOptional.get());
		copy.setActive(true);
		
		return service.createCopy(copy);
	}
	
	@RequestMapping(value="copy/update/{id}", method = RequestMethod.PATCH)
	public void update(@PathVariable Long id, @RequestBody Copy updatedCopy){
		Optional<Copy> existingCopy = service.findById(id);
		
		if (existingCopy.isPresent()) {
			Copy copy = existingCopy.get();
			copy.setActive(updatedCopy.isActive());		
			
			service.updateCopy(copy);
		}
	}
	
	/**
	 * Searches copies based on the given book id.
	 * 
	 * @param searchTerm the term to search for
	 * @return an iterable collection of copy matching the book id
	 */
	@RequestMapping("copies/search")
	public Iterable<Copy> searchCopies(@RequestParam long bookId) {
	    return service.searchCopies(bookId);
	}

}
