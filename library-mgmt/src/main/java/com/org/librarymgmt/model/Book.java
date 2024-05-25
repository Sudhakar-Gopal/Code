package com.org.librarymgmt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
@Schema(description = "Details about Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="unique ID")
	private long id;
	
	@Column(nullable=false)
	@Schema(description="ISBN of book")
	private String isbn;
	
	@Column(nullable = false)
	@Schema(description="Title of Book")
	private String title;
	
	@Column(nullable=false)
	@Schema(description="Author of Book")
	private String author;
	
	@Schema(description="To identify borrowed or not")
	private boolean borrowed;
	
	@ManyToOne
	@JoinColumn(name="borrower_id")
	@JsonBackReference
	@Schema(description="Borrower of book")
	private Borrower borrower;
	

}
