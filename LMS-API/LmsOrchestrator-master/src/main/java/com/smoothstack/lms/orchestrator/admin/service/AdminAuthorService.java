package com.smoothstack.lms.orchestrator.admin.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.Author;

@Service
public class AdminAuthorService {

	@Autowired
	private RestTemplate restTemplate;	
	
	public ResponseEntity<List<Author>> getAllAuthors(int size) {
		return restTemplate.exchange(
				  "http://admin-service/admin/authors",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Author>>(){});							
	}

	public ResponseEntity<Author> getAuthorById(Integer id) {		
		return restTemplate.getForEntity("http://admin-service/admin/author/" + id, Author.class);			
	}

	public ResponseEntity<Author> createAuthor(@Valid Author auth) {
		return restTemplate.postForEntity("http://admin-service/admin/author/", auth, Author.class);		
	}

	public ResponseEntity<Author> updateAuthor(@Valid Author auth) {
		restTemplate.put("http://admin-service/admin/author/", auth);
		return new ResponseEntity<Author>(auth, HttpStatus.OK);
	}

}
