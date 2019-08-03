package com.smoothstack.lms.orchestrator.borrower.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestrator.borrower.service.BorrowerService;
import com.smoothstack.lms.orchestrator.entity.BookCopies;
import com.smoothstack.lms.orchestrator.entity.BookLoan;
import com.smoothstack.lms.orchestrator.entity.Borrower;
import com.smoothstack.lms.orchestrator.entity.LibraryBranch;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";

	@Autowired
	private BorrowerService borrService;

	@PostMapping(value = "/login", produces = { JSON, XML }, consumes = { JSON, XML })
	public ResponseEntity<Borrower> loginBorrower(@RequestBody Borrower borrower) {
		return borrower.getCardNo() == null ? new ResponseEntity<Borrower>(HttpStatus.BAD_REQUEST)
				: borrService.loginBorrower(borrower.getCardNo());
	}

	@GetMapping(value = "/branches", produces = { JSON, XML })
	public ResponseEntity<List<LibraryBranch>> getAllBranches(
			@RequestParam(required = false, defaultValue = "100") int size) {
		return borrService.getAllBranches(size);
	}

	@GetMapping(value = "/branch/{branchId}/booksAvailable", produces = { XML, JSON })
	public ResponseEntity<List<BookCopies>> getAllAvailableBooksAtBranch(@Valid @PathVariable Integer branchId) {
		return borrService.getAllAvailableBooksAtBranch(branchId);
	}

	@PostMapping(value = "/checkoutBook", consumes = { XML, JSON }, produces = { XML, JSON })
	public ResponseEntity<BookLoan> checkOutBook(@RequestBody BookLoan bookLoan) {
		if (bookLoan.getBookLoanId() == null || bookLoan.getBookLoanId().getBook() == null
				|| bookLoan.getBookLoanId().getBorrower() == null || bookLoan.getBookLoanId().getBranch() == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		} else {
			return borrService.checkOutBook(bookLoan);
		}
	}

	@PutMapping(value = "/checkoutBook", consumes = { XML, JSON }, produces = { XML, JSON })
	public ResponseEntity<BookLoan> returnBook(@RequestBody BookLoan bookLoan) {
		if (bookLoan.getBookLoanId() == null || bookLoan.getBookLoanId().getBook() == null
				|| bookLoan.getBookLoanId().getBorrower() == null || bookLoan.getBookLoanId().getBranch() == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		} else {
			return borrService.returnBook(bookLoan);
		}
	}
}
