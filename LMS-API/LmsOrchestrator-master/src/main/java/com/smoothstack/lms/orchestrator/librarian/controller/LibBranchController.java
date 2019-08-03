package com.smoothstack.lms.orchestrator.librarian.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestrator.entity.LibraryBranch;
import com.smoothstack.lms.orchestrator.librarian.service.LibBranchService;

@RestController
@RequestMapping("/librarian")
public class LibBranchController {

	@Autowired
	private LibBranchService branchService;

	private final String XML = "application/xml";
	private final String JSON = "application/json";	

	@GetMapping(value ="/branches", produces = { XML, JSON })
	public ResponseEntity<List<LibraryBranch>> getAllBranches(@RequestParam(required = false, defaultValue = "100") int size) {
		return branchService.getAllBranches(size);
	}		
	
	@GetMapping(value = "/branch/{id}", produces = { XML, JSON })
	public ResponseEntity<LibraryBranch> getBranchById(@PathVariable Integer id) {
		return branchService.getBranchById(id);
	}

	@PutMapping(value ="/branch", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LibraryBranch>  updateBranch(@Valid @RequestBody LibraryBranch branch) {				
		return branchService.updateBranch(branch);
	}	
}
