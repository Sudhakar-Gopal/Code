package com.org.librarymgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.org.librarymgmt.model.Book;

@Service
public interface BookRepository extends JpaRepository<Book, Long>{

	public List<Book> findByIsbn(String isbn);
}
