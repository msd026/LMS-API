package com.smoothstack.lms.orchestrator.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.Borrower;

@Service
public class AdminBorrowerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<List<Borrower>> getAllBorrowers(int size) {
		return restTemplate.exchange(
				  "http://admin-service/admin/borrowers",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Borrower>>(){});	
	}

	public ResponseEntity<Borrower> getBorrowerById(Integer id) {
		return restTemplate.getForEntity("http://admin-service/admin/borrower/" + id, Borrower.class);		
	}

	public ResponseEntity<Borrower> createBorrower(Borrower borrower) {
		return restTemplate.postForEntity("http://admin-service/admin/borrower/", borrower, Borrower.class);			
	}	

	public ResponseEntity<Borrower> updateBorrower(Borrower borrower) {
		restTemplate.put("http://admin-service/admin/borrower/", borrower);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
	}
}
