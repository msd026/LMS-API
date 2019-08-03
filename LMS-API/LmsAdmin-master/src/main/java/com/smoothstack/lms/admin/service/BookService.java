package com.smoothstack.lms.admin.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smoothstack.lms.admin.dao.BookDAO;
import com.smoothstack.lms.admin.entity.Book;

@Service 
public class BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Transactional
	public List<Book> getAllBooks( int size) {
		Pageable limit = PageRequest.of(0,size);
		return bookDAO.findAll(limit).getContent();
	}	
	
	@Transactional
	public ResponseEntity<Book> getBookById(Integer id) {
		Optional<Book> book = bookDAO.findById(id);		
				
		return !book.isPresent() ? new ResponseEntity<Book>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Book>(book.get(), HttpStatus.OK);						 
	}	

	@Transactional
	public Book createBook(Book book) {
		return bookDAO.save(book);
	}	

	@Transactional
	public Book updateBook(Book book) {
		return bookDAO.save(book);
	}	
	
}
