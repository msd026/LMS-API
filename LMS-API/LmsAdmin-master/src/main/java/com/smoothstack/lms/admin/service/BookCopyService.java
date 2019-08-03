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

import com.smoothstack.lms.admin.dao.BookCopiesDAO;
import com.smoothstack.lms.admin.entity.Book;
import com.smoothstack.lms.admin.entity.BookCopies;
import com.smoothstack.lms.admin.entity.LibraryBranch;

@Service
public class BookCopyService {


	@Autowired
	private BookCopiesDAO bookCopyDAO;
	
	
	@Transactional
	public List<BookCopies> getAllBookCopies(int size) {
		Pageable limit = PageRequest.of(0,size);
		return bookCopyDAO.findAll(limit).getContent();
	}	
	
	@Transactional
	public ResponseEntity<BookCopies> getBookCopiesByBookAndBranch(Integer bookId, Integer branchId){
		if(bookId == null || branchId == null) {
			return new ResponseEntity<BookCopies>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<BookCopies> bookCopies = bookCopyDAO.findByBookAndBranchId(bookId, branchId);
		
		return bookCopies.isPresent() ? new ResponseEntity<BookCopies>(bookCopies.get(), HttpStatus.OK)
				: new ResponseEntity<BookCopies>(HttpStatus.NOT_FOUND);					
	}
	
	@Transactional
	public ResponseEntity<List<BookCopies>> getBookCopiesByBook(Integer bookId){
		if(bookId == null) {
			return new ResponseEntity<List<BookCopies>>(HttpStatus.BAD_REQUEST);
		}		
		
		Optional<List<BookCopies>> listBookCopies = bookCopyDAO.findByBookId(bookId); 
		
		return listBookCopies.isPresent() ? new ResponseEntity<List<BookCopies>>(listBookCopies.get(), HttpStatus.OK)
				: new ResponseEntity<List<BookCopies>>(HttpStatus.NOT_FOUND);		
	}
	
	@Transactional
	public ResponseEntity<List<BookCopies>> getBookCopiesOfBranch(Integer branchId){
		if(branchId == null) {
			return new ResponseEntity<List<BookCopies>>(HttpStatus.BAD_REQUEST);
		}		
		
		Optional<List<BookCopies>> listBookCopies = bookCopyDAO.findByBranchId(branchId); 
		
		return listBookCopies.isPresent() ? new ResponseEntity<List<BookCopies>>(listBookCopies.get(), HttpStatus.OK)
				: new ResponseEntity<List<BookCopies>>(HttpStatus.NOT_FOUND);		

	}
	
	@Transactional
	public BookCopies addBookCopies(BookCopies bookCopies) {		
		
		return bookCopyDAO.save(bookCopies);			
	}	

	@Transactional
	public ResponseEntity<BookCopies> updateBookCopies(BookCopies bookCopies) {
		Book book = bookCopies.getBookCopyId().getBook();
		LibraryBranch branch  = bookCopies.getBookCopyId().getBranch();
		
		if(book == null || branch == null || bookCopies.getNoOfCopies() == null) {
			return new ResponseEntity<BookCopies>(HttpStatus.BAD_REQUEST);			
		}
		
		bookCopyDAO.update(book.getBookId(), branch.getBranchId(), bookCopies.getNoOfCopies());
		
		return new ResponseEntity<BookCopies>(HttpStatus.OK);
	}	
}
