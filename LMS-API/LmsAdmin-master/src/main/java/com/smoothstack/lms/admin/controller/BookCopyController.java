package com.smoothstack.lms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.admin.entity.BookCopies;
import com.smoothstack.lms.admin.service.BookCopyService;

@RestController
@RequestMapping("/admin")
public class BookCopyController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";	
	
	@Autowired
	private BookCopyService bookCopyService;
	
	
	@GetMapping(value = "/number-of-copies", produces = { XML, JSON })
	public List<BookCopies> getAllBookCopies(@RequestParam(required = false, defaultValue = "100") int size) {		
		return bookCopyService.getAllBookCopies(size);
	}
	
	
	@GetMapping(value = "/number-of-copies/ofBook/{bookId}/inBranch/{branchId}", produces = { XML, JSON })
	public ResponseEntity<BookCopies> getBookCopiesByBookAndBranch(@PathVariable Integer bookId, @PathVariable Integer branchId){
		return bookCopyService.getBookCopiesByBookAndBranch(bookId, branchId);								
	}
	
	@GetMapping(value = "/number-of-copies/ofBook/{bookId}", produces = { XML, JSON })
	public ResponseEntity<List<BookCopies>> getBookCopiesByBook(@PathVariable Integer bookId){
		return bookCopyService.getBookCopiesByBook(bookId);			
	}
	
	@GetMapping(value = "/number-of-copies/inBranch/{branchId}", produces = { XML, JSON })
	public ResponseEntity<List<BookCopies>> getBookCopiesOfBranch(@PathVariable Integer branchId){
		return bookCopyService.getBookCopiesOfBranch(branchId);		

	}
	
	@PostMapping(value = "/number-of-copies", produces = "application/json", consumes = { XML, JSON })
	public BookCopies addBookCopies(@RequestBody BookCopies bookCopies) {		
		return bookCopyService.addBookCopies(bookCopies);
		
	}
	
	@PutMapping(value = "/number-of-copies", produces = "application/json", consumes = { XML, JSON })
	public ResponseEntity<BookCopies> updateBookCopies(@RequestBody BookCopies bookCopies) {
		return bookCopyService.updateBookCopies(bookCopies);
	}	
}

