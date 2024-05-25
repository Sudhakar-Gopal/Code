package com.org.librarymgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<?> handleException(LibraryManagementException ex, WebRequest request) {
		HttpStatus httpStatus;
		switch (ex.getErrorCode()) {
		case NOT_FOUND:
		case BORROWER_MISMATCH:
			httpStatus = HttpStatus.NOT_FOUND;
			break;
		case ISBN_CONFLICT:
		case BOOK_ALREADY_BORROWED:
		case BOOK_NOT_BORROWED:
			httpStatus = HttpStatus.CONFLICT;
			break;
		default:
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			break;

		}
		return new ResponseEntity<>(ex.getMessage(), httpStatus);
	}
}
