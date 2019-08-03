package com.smoothstack.lms.admin.controller;

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

import com.smoothstack.lms.admin.entity.Borrower;
import com.smoothstack.lms.admin.service.AdminBorrowerService;

@RestController
@RequestMapping("/admin")
public class AdminBorrowerController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private AdminBorrowerService borrService;
	
	@GetMapping(value ="/borrowers", produces = { XML, JSON })
	public List<Borrower> getAllBorrowers(@RequestParam(required = false, defaultValue = "100") int size) {		
		return borrService.getAllBorrowers(size);
	}		
	
	@GetMapping(value = "/borrower/{cardNo}", produces = { XML, JSON })
	public ResponseEntity<Borrower> getBorrowerById(@PathVariable Integer cardNo) {
		return borrService.getBorrowerById(cardNo);								 
	}
	
	@PostMapping(value ="/borrower", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public Borrower createBorrower(@Valid @RequestBody Borrower borr) {
		return borrService.createBorrower(borr);
	}	

	@PutMapping(value ="/borrower", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public Borrower updateBorrower(@Valid @RequestBody Borrower borr) {
		return borrService.updateBorrower(borr);
	}	
	
}
