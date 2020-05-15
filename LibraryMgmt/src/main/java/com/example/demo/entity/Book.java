/**
 * 
 */
package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Gokul
 *
 */
@Entity
@Table(uniqueConstraints = {
	      @UniqueConstraint(columnNames = "isbn",name = "BOOK")}
)
public class Book implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "BOOK_ID")
	@JsonIgnore
	private Long bookId;
	
	@Column(name = "BOOK_NAME")
	private String bookName;

	@Column(name = "PUBLISHER")
	private String publisher;
	
	@Column(name = "ISBN")
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name = "LIB_ID")
	Library library;

	public Book() {}
	
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	
	
}
