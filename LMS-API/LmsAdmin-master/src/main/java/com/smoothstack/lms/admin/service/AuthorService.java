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


import com.smoothstack.lms.admin.dao.AuthorDAO;
import com.smoothstack.lms.admin.entity.Author;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorDAO authDAO;
	
	@Transactional
	public List<Author> getAllAuthors(int size) {
		Pageable limit = PageRequest.of(0,size);
		return authDAO.findAll(limit).getContent();
	}
	
	@Transactional
	public ResponseEntity<Author> getAuthorById(Integer id) {
		Optional<Author> auth = authDAO.findById(id);		
				
		return !auth.isPresent() ? new ResponseEntity<Author>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Author>(auth.get(), HttpStatus.OK);						 
	}
	
	@Transactional
	public Author createAuthor(Author auth) {
		return authDAO.save(auth);
	}	

	@Transactional
	public Author updateAuthor(Author auth) {
		return authDAO.save(auth);
	}	
	

}
