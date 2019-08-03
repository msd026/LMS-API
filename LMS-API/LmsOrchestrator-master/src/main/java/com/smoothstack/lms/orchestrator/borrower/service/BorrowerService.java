package com.smoothstack.lms.orchestrator.borrower.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.BookCopies;
import com.smoothstack.lms.orchestrator.entity.BookLoan;
import com.smoothstack.lms.orchestrator.entity.Borrower;
import com.smoothstack.lms.orchestrator.entity.LibraryBranch;

@Service
public class BorrowerService {

	@Autowired
	private RestTemplate restTemplate;

	
	public ResponseEntity<List<LibraryBranch>> getAllBranches(int size) {
		return restTemplate.exchange("http://borrower-service/borrower/branches", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<LibraryBranch>>() {
				});
	}
	
	
	public ResponseEntity<Borrower> loginBorrower(Integer cardNo) {
		return restTemplate.exchange("http://borrower-service/borrower/login", HttpMethod.POST, new HttpEntity<Integer>(cardNo), 
				new ParameterizedTypeReference<Borrower>() {});
		}
	
	public ResponseEntity<List<BookCopies>> getAllAvailableBooksAtBranch(Integer branchId){
		return restTemplate.exchange("http://borrower-service/borrower/branch/"+branchId+"/booksAvailable", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BookCopies>>() {});
	}
	

	public ResponseEntity<BookLoan> checkOutBook(BookLoan bookLoan){
		return restTemplate.postForEntity("http://admin-service/borrower/checkoutBook/", bookLoan, BookLoan.class);
	}
	

	public ResponseEntity<BookLoan> returnBook(BookLoan bookLoan){
		restTemplate.put("http://borrower-service/borrower/return" + bookLoan, BookLoan.class);
		return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.OK);
	}
	
	
}