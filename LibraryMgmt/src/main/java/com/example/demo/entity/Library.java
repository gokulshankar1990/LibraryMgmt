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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Gokul
 *
 */
@Entity
@Table(uniqueConstraints = {
	      @UniqueConstraint(columnNames = "NUM", name = "LIBRARY")}
)
public class Library implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="LIB_ID")
	@JsonIgnore
	private Long libId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NUM")
	private String libraryNum;
	
	public Library() {
		// TODO Auto-generated constructor stub
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_Id")
	private Address address;
	
	@OneToMany(mappedBy = "library")
	@JsonIgnore
	private Set<Book> books = new HashSet<Book>();

	public Long getLibId() {
		return libId;
	}

	public void setLibId(Long libId) {
		this.libId = libId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLibraryNum() {
		return libraryNum;
	}

	public void setLibraryNum(String libraryNum) {
		this.libraryNum = libraryNum;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
