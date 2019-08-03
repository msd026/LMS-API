package com.smoothstack.lms.orchestrator.librarian.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.BookCopies;

@Service
public class LibBookCopyService {

	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<List<BookCopies>> getAllBookCopies(int size) {
		return restTemplate.exchange(
				  "http://librarian-service/librarian/number-of-copies",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<BookCopies>>(){});	
	}

	public ResponseEntity<List<BookCopies>> getBookCopiesByBookAndBranch(Integer bookId, Integer branchId) {
		return restTemplate.exchange(
				"http://librarian-service/librarian/number-of-copies/ofBook/" + bookId +"/inBranch/" + branchId ,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<BookCopies>>(){});				
	}

	public ResponseEntity<List<BookCopies>> getBookCopiesByBook(Integer bookId) {
		return restTemplate.exchange(
				"http://librarian-service/librarian/number-of-copies/ofBook/" + bookId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<BookCopies>>(){});	
	}

	public ResponseEntity<List<BookCopies>> getBookCopiesOfBranch(Integer branchId) {
		return restTemplate.exchange(
				"http://librarian-service/librarian/number-of-copies/inBranch/" + branchId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<BookCopies>>(){});
	}

	public ResponseEntity<BookCopies> addBookCopies(BookCopies bookCopies) {
		return restTemplate.postForEntity("http://librarian-service/librarian/number-of-copies/", bookCopies, BookCopies.class);
		
	}

	public ResponseEntity<BookCopies> updateBookCopies(BookCopies bookCopies) {
		restTemplate.put("http://librarian-service/librarian/number-of-copies/", bookCopies);
		return new ResponseEntity<BookCopies>(bookCopies, HttpStatus.OK);
		
	}
	
	
}
