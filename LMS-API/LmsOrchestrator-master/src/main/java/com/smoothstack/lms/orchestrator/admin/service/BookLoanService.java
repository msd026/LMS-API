package com.smoothstack.lms.orchestrator.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.BookLoan;

@Service
public class BookLoanService {
	
	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<BookLoan> extendDueDate(BookLoan loan) {
		restTemplate.put("http://admin-service/admin/extend-due-date/", loan);
		return new ResponseEntity<BookLoan>(loan, HttpStatus.OK);
		
	}

}
