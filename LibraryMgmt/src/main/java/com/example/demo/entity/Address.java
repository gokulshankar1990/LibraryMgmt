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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Gokul
 *
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="ADDR_ID")
	@JsonIgnore
	private Long addressId;
	
	@Column(name = "LOCATION")
	private String location;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Library library;
	
	public Address() {}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}
	
}
