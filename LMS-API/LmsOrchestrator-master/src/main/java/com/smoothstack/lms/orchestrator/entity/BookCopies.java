package com.smoothstack.lms.orchestrator.entity;





public class BookCopies {

	
	private BookCopyId bookCopyId;		
	private Integer noOfCopies;
	

	public Integer getNoOfCopies() { return noOfCopies; } 
	public void setNoOfCopies(Integer noOfCopies) { this.noOfCopies = noOfCopies; }
	
	
	public BookCopyId getBookCopyId() {
		return bookCopyId;
	}
	public void setBookCopyId(BookCopyId bookCopyId) {
		this.bookCopyId = bookCopyId;
	}
	
}
