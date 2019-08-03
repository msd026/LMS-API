package com.smoothstack.lms.orchestrator.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.Book;

@Service
public class BookService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<List<Book>> getAllBooks(int size) {
		return restTemplate.exchange(
				  "http://admin-service/admin/books",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Book>>(){});	
	}

	public ResponseEntity<Book> getBookById(Integer id) {
		return restTemplate.getForEntity("http://admin-service/admin/book/" + id, Book.class);		
	}

	public ResponseEntity<Book> createBook(Book book) {
		return restTemplate.postForEntity("http://admin-service/admin/book/", book, Book.class);			
	}	

	public ResponseEntity<Book> updateBook(Book book) {
		restTemplate.put("http://admin-service/admin/book/", book);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

}
