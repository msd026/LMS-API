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

import com.smoothstack.lms.admin.dao.BranchDAO;
import com.smoothstack.lms.admin.entity.LibraryBranch;

@Service
public class BranchService {	

	@Autowired
	private BranchDAO branchDAO;
	
	@Transactional
	public List<LibraryBranch> getAllBranches(int size) {
		Pageable limit = PageRequest.of(0,size);
		return branchDAO.findAll(limit).getContent();
	}		
	
	@Transactional
	public ResponseEntity<LibraryBranch> getBranchById(Integer id) {
		Optional<LibraryBranch> branch = branchDAO.findById(id);		
				
		return !branch.isPresent() ? new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<LibraryBranch>(branch.get(), HttpStatus.OK);						 
	}	

	@Transactional
	public LibraryBranch createBranch( LibraryBranch branch) {
		return branchDAO.save(branch);
	}	

	@Transactional
	public LibraryBranch  updateBranch( LibraryBranch branch) {				
		return branchDAO.save(branch);
	}	
	
}
