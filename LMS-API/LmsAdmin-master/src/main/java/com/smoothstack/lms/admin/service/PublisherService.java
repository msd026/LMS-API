package com.smoothstack.lms.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.lms.admin.dao.PublisherDAO;
import com.smoothstack.lms.admin.entity.Publisher;

@Service
public class PublisherService {	
	
	@Autowired
	private PublisherDAO pubDAO;
	
	@Transactional
	public List<Publisher> getAllPublishers(int size) {
		Pageable limit = PageRequest.of(0,size);
		return pubDAO.findAll(limit).getContent();
	}	
	
	@Transactional
	public ResponseEntity<Publisher> getPublisherById(Integer id) {
		Optional<Publisher> pub = pubDAO.findById(id); 	
		
		return pub.isPresent() ? new ResponseEntity<Publisher>(pub.get(), HttpStatus.OK)
				: new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);							 
	}	
	
	@Transactional
	public Publisher createPublisher(Publisher pub) {
		return pubDAO.save(pub);
	}	

	@Transactional
	public Publisher updatePublisher(Publisher pub) {
		return pubDAO.save(pub);
	}	
}
