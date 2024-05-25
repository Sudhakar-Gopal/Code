package com.org.librarymgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.librarymgmt.model.Book;
import com.org.librarymgmt.model.Borrower;
import com.org.librarymgmt.service.LibraryManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="LibraryManagement", description = "API for managing books in Library")
@RequestMapping("/api/v1")
public class LibraryManagementController {
	
	@Autowired
	private LibraryManagementService libraryManagementService;
	
	
	@PostMapping("/books")
	@Operation(description = "Register a Book")
	public ResponseEntity<Book> registerBooks(@RequestBody Book book)
	{
		Book savedBook = libraryManagementService.registerBook(book);
		return ResponseEntity.ok(savedBook);
	}
	
	@PostMapping("/borrowers")
	@Operation(description = "Register a Borrower")
	public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower)
	{
		Borrower savedBorrower= libraryManagementService.registerBorrower(borrower);
		return ResponseEntity.ok(savedBorrower);
	}
	
	@PostMapping("/books")
	@Operation(description = "Get all list of books")
	public ResponseEntity<List<Book>> getAllBooks()
	{
		List<Book> books = libraryManagementService.getAllBooks();
		return ResponseEntity.ok(books);
	}
	

	@PostMapping("/borrower/{borrowerId}/borrow/{bookId}")
	@Operation(description = "Borrow a Book based on BookId and BorrowerId")
	public ResponseEntity<Book> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId)
	{
		Book borrowedBook = libraryManagementService.borrowBook(bookId, borrowerId);
		return ResponseEntity.ok(borrowedBook);
	}
	
	@PostMapping("/borrower/{borrowerId}/return/{bookId}")
	@Operation(description = "Return a Book")
	public ResponseEntity<Book> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId)
	{
		Book borrowedBook = libraryManagementService.returnBook(bookId, borrowerId);
		return ResponseEntity.ok(borrowedBook);
	}


}
