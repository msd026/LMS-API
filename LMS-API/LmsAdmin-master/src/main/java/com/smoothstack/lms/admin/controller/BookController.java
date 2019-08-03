package com.smoothstack.lms.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.admin.entity.Book;
import com.smoothstack.lms.admin.service.BookService;

@RestController
@RequestMapping("/admin")
public class BookController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping(value ="/books", produces = { XML, JSON })
	public List<Book> getAllBooks(@RequestParam(required = false, defaultValue = "100") int size) {		
		return bookService.getAllBooks(size);
	}	
	
	
	@GetMapping(value = "/book/{id}", produces = { XML, JSON })
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);											 
	}
	
	@PostMapping(value ="/book", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@Valid @RequestBody Book book) {
		return bookService.createBook(book);
	}	

	@PutMapping(value ="/book", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public Book updateBook(@Valid @RequestBody Book book) {
		return bookService.updateBook(book);
	}	
	
}
