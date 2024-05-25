package com.org.librarymgmt.exception;

public class LibraryManagementException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ErrorCode{
		NOT_FOUND,
		BORROWER_MISMATCH,
		BOOK_ALREADY_BORROWED,
		BOOK_NOT_BORROWED,
		ISBN_CONFLICT
	}
	
	private final ErrorCode errorCode;
	
	public LibraryManagementException(String message, ErrorCode errorCode)
	{
		super(message);
		this.errorCode=errorCode;
	}
	
	public ErrorCode getErrorCode()
	{
		return errorCode;
	}
}
