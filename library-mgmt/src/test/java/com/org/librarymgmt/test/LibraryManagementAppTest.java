package com.org.librarymgmt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.org.librarymgmt.model.Book;
import com.org.librarymgmt.model.Borrower;
import com.org.librarymgmt.repository.BookRepository;
import com.org.librarymgmt.repository.BorrowerRepository;
import com.org.librarymgmt.service.impl.LibraryManagementServiceImpl;

@SpringBootTest
@WebMvcTest(value = LibraryManagementAppTest.class)
class LibraryManagementAppTest {

	@InjectMocks
	private LibraryManagementServiceImpl serviceImpl;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private BorrowerRepository borrowerRepository;
	
	@BeforeEach
	void setup()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void registerBook()
	{
		Book book =new Book();
		book.setId(1L);
		book.setIsbn("124r-fhs8-oj8s");
		book.setAuthor("Andy");
		book.setTitle("Fly High");
		
		when(bookRepository.save(book)).thenReturn(book);
		
		Book savedBook= serviceImpl.registerBook(book);
		
		assertNotNull(savedBook);
		assertEquals(book.getTitle(), savedBook.getTitle());
		
		verify(bookRepository,times(1)).save(book);
	}
	
	@Test
	void registerBorrower()
	{
		Borrower borrower = new Borrower();
		borrower.setId(1L);
		borrower.setName("Sudhakar");
		borrower.setEmail("sudhakarg@gmail.com");
		when(borrowerRepository.save(borrower)).thenReturn(borrower);
		
		Borrower savedborrower= serviceImpl.registerBorrower(borrower);
		
		assertNotNull(savedborrower);
		assertEquals(savedborrower.getName(), savedborrower.getName());
		
		verify(borrowerRepository,times(1)).save(borrower);
	}
	
	@Test
	void getAllBooks()
	{
		Book book =new Book();
		book.setId(1L);
		book.setIsbn("124r-fhs8-oj8s");
		book.setAuthor("Andy");
		book.setTitle("Fly High");
		
		Book book1 =new Book();
		book.setId(2L);
		book.setIsbn("1we4r-c3fhs8-3roj8s");
		book.setAuthor("Ben");
		book.setTitle("Sucess Ratio");
		when(borrowerRepository.findAll()).thenReturn(Arrays.asList(book,book1));
		
		List<Book> books=serviceImpl.getAllBooks();
		
		assertNotNull(books);
		assertEquals(2, books.size());
		
		verify(bookRepository,times(1)).findAll();
		
	}
	
	@Test
	void borrowBooks()
	{
		Long bookId=1L;
		Long borrowerId=2L;
		Book book1 =new Book();
		book1.setId(bookId);
		book1.setIsbn("1we4r-c3fhs8-3roj8s");
		book1.setAuthor("Ben");
		book1.setTitle("Sucess Ratio");
		
		Borrower borrower1 = new Borrower();
		borrower1.setId(borrowerId);
		borrower1.setName("Sudhakar");
		borrower1.setEmail("sudhakarg@gmail.com");
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book1));
		when(borrowerRepository.findById(borrowerId)).thenReturn(Optional.of(borrower1));
		when(bookRepository.save(book1)).thenReturn(book1);
		
		Book borrowedBook= serviceImpl.borrowBook(bookId, borrowerId);
		
		assertNotNull(borrowedBook);
		assertTrue(borrowedBook.isBorrowed());
		assertEquals(borrowedBook.getBorrower().getName(), borrower1.getName());
		
		verify(bookRepository,times(1)).findById(bookId);
		verify(borrowerRepository,times(1)).findById(borrowerId);
		verify(bookRepository,times(1)).save(book);
		
	}
	
	@Test
	void returnBooks()
	{
		Long bookId=1L;
		Long borrowerId=2L;
		Book book1 =new Book();
		book1.setId(bookId);
		book1.setIsbn("1we4r-c3fhs8-3roj8s");
		book1.setAuthor("Ben");
		book1.setTitle("Sucess Ratio");
		
		Borrower borrower1 = new Borrower();
		borrower1.setId(borrowerId);
		borrower1.setName("Sudhakar");
		borrower1.setEmail("sudhakarg@gmail.com");
		book1.setBorrower(borrower1);
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book1));
		when(borrowerRepository.findById(borrowerId)).thenReturn(Optional.of(borrower1));
		when(bookRepository.save(book1)).thenReturn(book1);
		
		Book returnBook= serviceImpl.returnBook(bookId, borrowerId);
		
		assertNull(returnBook);
		assertTrue(!borrowedBook.isBorrowed());
		
		verify(bookRepository,times(1)).findById(bookId);
		verify(borrowerRepository,times(1)).findById(borrowerId);
		verify(bookRepository,times(1)).save(book);
		
	}
	

}
