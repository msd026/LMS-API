package com.smoothstack.lms.orchestrator.librarian.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.LibraryBranch;

@Service
public class LibBranchService {

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<List<LibraryBranch>> getAllBranches(int size) {
		return restTemplate.exchange(
				  "http://librarian-service/librarian/branches",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<LibraryBranch>>(){});	
	}

	public ResponseEntity<LibraryBranch> getBranchById(Integer id) {
		return restTemplate.getForEntity("http://librarian-service/librarian/branch/" + id, LibraryBranch.class);		
	}

	public ResponseEntity<LibraryBranch> createBranch(LibraryBranch branch) {
		return restTemplate.postForEntity("http://librarian-service/librarian/branch/", branch, LibraryBranch.class);			
	}
	

	public ResponseEntity<LibraryBranch> updateBranch(@Valid LibraryBranch branch) {
		restTemplate.put("http://librarian-service/librarian/branch/", branch);
		return new ResponseEntity<LibraryBranch>(branch, HttpStatus.OK);
	}
	
	
	
}
