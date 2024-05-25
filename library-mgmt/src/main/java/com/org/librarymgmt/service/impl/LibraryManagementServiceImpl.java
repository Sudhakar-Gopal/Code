package com.org.librarymgmt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.librarymgmt.exception.LibraryManagementException;
import com.org.librarymgmt.exception.LibraryManagementException.ErrorCode;
import com.org.librarymgmt.model.Book;
import com.org.librarymgmt.model.Borrower;
import com.org.librarymgmt.repository.BookRepository;
import com.org.librarymgmt.repository.BorrowerRepository;
import com.org.librarymgmt.service.LibraryManagementService;

@Service
public class LibraryManagementServiceImpl implements LibraryManagementService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowerRepository borrowerRepository;

	@Override
	public Book registerBook(Book book) {

		List<Book> booksWithSameIsbn = bookRepository.findByIsbn(book.getIsbn());

		for (Book existingBook : booksWithSameIsbn) {
			if (!existingBook.getTitle().equals(book.getTitle())
					|| (!existingBook.getAuthor().equals(book.getAuthor()))) {
				throw new LibraryManagementException("Book with Same ISBN must have Same Author and Title",
						ErrorCode.ISBN_CONFLICT);
			}
		}

		return bookRepository.save(book);

	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Borrower registerBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
	}

	@Override
	public Book borrowBook(Long bookId, Long borrowerId) {
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		Optional<Borrower> borrowerOpt = borrowerRepository.findById(borrowerId);

		if (bookOpt.isPresent() && borrowerOpt.isPresent()) {
			Book book = bookOpt.get();
			Borrower borrower = borrowerOpt.get();

			if (book.isBorrowed()) {
				throw new LibraryManagementException("Book is already borrowed", ErrorCode.BOOK_ALREADY_BORROWED);
			} else {
				book.setBorrowed(true);
				book.setBorrower(borrower);
				return bookRepository.save(book);
			}
		} else {
			throw new LibraryManagementException("Book or Borrower Id is Invalid", ErrorCode.NOT_FOUND);
		}
	}

	@Override
	public Book returnBook(Long bookId, Long borrowerId) {
		Optional<Book> bookOpt = bookRepository.findById(bookId);
		Optional<Borrower> borrowerOpt = borrowerRepository.findById(borrowerId);

		if (bookOpt.isPresent() && borrowerOpt.isPresent()) {
			Book book = bookOpt.get();
			Borrower borrower = borrowerOpt.get();

			if (!book.isBorrowed()) {
				throw new LibraryManagementException("Book is not already borrowed", ErrorCode.BOOK_NOT_BORROWED);
			} 
			if (!borrowerId.equals(Long.valueOf(book.getBorrower().getId()))) {
				throw new LibraryManagementException("Borrower Id is not matching", ErrorCode.BORROWER_MISMATCH);
			} 
			
			else {
				book.setBorrowed(false);
				book.setBorrower(null);
				return bookRepository.save(book);
			}
		} else {
			throw new LibraryManagementException("Book or Borrower Id is Invalid", ErrorCode.NOT_FOUND);
		}
	}

}
