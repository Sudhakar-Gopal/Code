package com.org.librarymgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.org.librarymgmt.model.Borrower;

@Service
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

}
