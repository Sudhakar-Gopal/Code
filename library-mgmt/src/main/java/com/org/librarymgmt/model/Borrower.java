package com.org.librarymgmt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
@Schema(description = "Details about Borrower")
public class Borrower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="unique ID")
	private long id;
	
	@Schema(description="Name of Borrower")
	private String name;
	
	@Schema(description="Email of Borrower")
	private String email;
	
	@OneToMany(mappedBy = "borrower")
	@JsonManagedReference
	@Schema(description="List of books borrowed by borrower")
	private List<Book> books;
}
