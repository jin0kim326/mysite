package com.douzone.mysite.exception;

public class GuestbookRepositoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuestbookRepositoryException() {
		super("GeuxtbookRepositoryException occur");
	}
	
	public GuestbookRepositoryException(String message) {
		super(message);
	}

}
