package com.org.librarymgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.org.librarymgmt.model.Book;
import com.org.librarymgmt.model.Borrower;

@Service
public interface LibraryManagementService {
	Book registerBook(Book book);
	List<Book> getAllBooks();
	Borrower registerBorrower(Borrower borrower);
	Book borrowBook(Long bookId, Long borrowerId);
	Book returnBook(Long bookId, Long borrowerId);
}
