package com.smoothstack.lms.orchestrator.librarian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestrator.librarian.service.LibBookService;
import com.smoothstack.lms.orchestrator.entity.Book;

@RestController
@RequestMapping("/librarian")
public class LibBookController {
	
	private final String XML = "application/xml";
	private final String JSON = "application/json";

	@Autowired
	private LibBookService bookService;	
	
	@GetMapping(value ="/books", produces = { XML, JSON })
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false, defaultValue = "100") int size) {		
		return bookService.getAllBooks(size);
	}	
	
	
	@GetMapping(value = "/book/{id}", produces = { XML, JSON })
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);						 
	}
	
}
