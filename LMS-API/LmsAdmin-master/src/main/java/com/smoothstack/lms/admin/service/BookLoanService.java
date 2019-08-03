package com.smoothstack.lms.admin.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.lms.admin.dao.BookLoanDAO;
import com.smoothstack.lms.admin.entity.BookLoan;

@Service
public class BookLoanService {

	@Autowired
	private BookLoanDAO loanDAO;

	@Transactional
	public ResponseEntity<BookLoan> extendDueDate(BookLoan loan) {
		int bookId = loan.getBookLoanId().getBook().getBookId();
		int branchId = loan.getBookLoanId().getBranch().getBranchId();
		int cardNo = loan.getBookLoanId().getBorrower().getCardNo();
		LocalDateTime dueDate = loan.getDueDate();		
	
		return loanDAO.extendDueDate(dueDate, bookId, branchId, cardNo);
	}
	
	
	
	
}
