package com.gcit.training.lms.entity;

public class Book_copies {
	private Book book;
	private Library_branch branch;
	private int noOfCopies;
	
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Library_branch getBranch() {
		return branch;
	}
	public void setBranch(Library_branch branch) {
		this.branch = branch;
	}
}
