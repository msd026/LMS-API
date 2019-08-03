package com.smoothstack.lms.admin.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.admin.entity.BookLoan;
import com.smoothstack.lms.admin.entity.BookLoanId;

@Repository
public interface BookLoanDAO extends JpaRepository<BookLoan, BookLoanId>{

	@Modifying
	@Query(value = "UPDATE tbl_book_loan SET dueDate = ?1 WHERE bookId = ?2 AND branchId = ?3 AND cardNo = ?4",
			nativeQuery = true)
	ResponseEntity<BookLoan> extendDueDate(LocalDateTime dueDate, int bookId, int branchId, int cardNo);

}
