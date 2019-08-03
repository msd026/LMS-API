package com.smoothstack.lms.orchestrator.librarian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.Book;

@Service
public class LibBookService {

	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<List<Book>> getAllBooks(int size) {
		return restTemplate.exchange(
				  "http://librarian-service/librarian/books",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Book>>(){});	
	}

	public ResponseEntity<Book> getBookById(Integer id) {
		return restTemplate.getForEntity("http://librarian-service/librarian/book/" + id, Book.class);		
	}	
	
}
