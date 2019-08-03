package com.smoothstack.lms.orchestrator.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestrator.admin.service.AdminAuthorService;
import com.smoothstack.lms.orchestrator.entity.Author;;

@RestController
@RequestMapping("/admin")
public class AuthorController {
	
	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	AdminAuthorService authService;
	
	
	@GetMapping(value ="/authors", produces = { XML, JSON })
	public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(required = false, defaultValue = "100") int size) {		
		return authService.getAllAuthors(size);
	}	
	
	
	@GetMapping(value = "/author/{id}", produces = { XML, JSON })
	public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
		return authService.getAuthorById(id);								 
	}
	
	@PostMapping(value ="/author", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author auth) {
		return authService.createAuthor(auth);
	}	

	@PutMapping(value ="/author", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Author> updateAuthor(@Valid @RequestBody Author auth) {
		return authService.updateAuthor(auth);
	}	
	
}
