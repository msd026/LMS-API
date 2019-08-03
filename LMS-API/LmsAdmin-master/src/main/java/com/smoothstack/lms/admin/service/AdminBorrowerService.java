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

import com.smoothstack.lms.admin.dao.AdminBorrowerDAO;
import com.smoothstack.lms.admin.entity.Borrower;

@Service
public class AdminBorrowerService {
	
	@Autowired
	private AdminBorrowerDAO borrDAO;

	@Transactional
	public List<Borrower> getAllBorrowers(int size) {
		Pageable limit = PageRequest.of(0,size);
		return borrDAO.findAll(limit).getContent();
	}		
	
	@Transactional
	public ResponseEntity<Borrower> getBorrowerById(Integer cardNo) {
		Optional<Borrower> borr = borrDAO.findById(cardNo);		
				
		return !borr.isPresent() ? new ResponseEntity<Borrower>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Borrower>(borr.get(), HttpStatus.OK);						 
	}
	
	@Transactional
	public Borrower createBorrower(Borrower borr) {
		return borrDAO.save(borr);
	}	

	@Transactional
	public Borrower updateBorrower(Borrower borr) {
		return borrDAO.save(borr);
	}	
}
