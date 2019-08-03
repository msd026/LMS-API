package com.smoothstack.lms.orchestrator.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestrator.entity.Publisher;

@Service
public class PublisherService {

	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<List<Publisher>> getAllPublishers(int size) {
		return restTemplate.exchange(
				  "http://admin-service/admin/publishers",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Publisher>>(){});	
	}

	public ResponseEntity<Publisher> getPublisherById(Integer id) {
		return restTemplate.getForEntity("http://admin-service/admin/publisher/" + id, Publisher.class);		
	}

	public ResponseEntity<Publisher> createPublisher(Publisher pub) {
		return restTemplate.postForEntity("http://admin-service/admin/publisher/", pub, Publisher.class);			
	}
	

	public ResponseEntity<Publisher> updatePublisher(Publisher pub) {
		restTemplate.put("http://admin-service/admin/publisher/", pub);
		return new ResponseEntity<Publisher>(pub, HttpStatus.OK);
	}

}
