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

import com.smoothstack.lms.orchestrator.admin.service.PublisherService;
import com.smoothstack.lms.orchestrator.entity.Publisher;

@RestController
@RequestMapping("/admin")
public class PublisherController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private PublisherService pubService;
	
	@GetMapping(value ="/publishers", produces = { XML, JSON })
	public ResponseEntity<List<Publisher>> getAllPublishers(@RequestParam(required = false, defaultValue = "100") int size) {
		return pubService.getAllPublishers(size);
	}	
	
	
	@GetMapping(value = "/publisher/{id}", produces = { XML, JSON })
	public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer id) {
		return pubService.getPublisherById(id);							 
	}
	
	@PostMapping(value ="/publisher", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Publisher> createPublisher(@Valid @RequestBody Publisher pub) {
		return pubService.createPublisher(pub);
	}	

	@PutMapping(value ="/publisher", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Publisher> updatePublisher(@Valid @RequestBody Publisher pub) {
		return pubService.updatePublisher(pub);
	}	
	
	
}
