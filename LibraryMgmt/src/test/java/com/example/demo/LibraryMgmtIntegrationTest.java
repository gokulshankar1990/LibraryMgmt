/**
 * 
 */
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsRestAssuredConfigurationCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.demo.entity.Address;
import com.example.demo.entity.Book;
import com.example.demo.entity.Library;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * @author Gokul
 *
 */
public class LibraryMgmtIntegrationTest {
	
	private static final String API_ROOT
    = "http://localhost:8080/lib-mgmt/library";
	
	private Library createLibrary(String libNum,String libName,String locationName)
	{
		Library library = new Library();
		library.setLibraryNum(libNum);
		library.setName(libName);
		library.setAddress(createAddress(locationName));
		return library;
		
	}
	
	private Address createAddress(String locationNme)
	{
		Address address = new Address();
		address.setLocation("Chennai");
		return address;
	}
	
	private Book createBook()
	{
		Book book = new Book();
		book.setBookName("History");
		book.setIsbn("AAW128");
		book.setPublisher("Offset printer");
		return book;
	}
	
	@Test
	public void test_newcreateLibrary_success()
	{
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(createLibrary("A001","Chennai Lib", "Chennai")).post(API_ROOT);
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}
	
	@Test
	public void test_duplicatecreateLibrary_forFailure()
	{
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(createLibrary("A002","richy Lib", "Trichy")).post(API_ROOT);
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		
		response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(createLibrary("A002","richy Lib", "Trichy")).post(API_ROOT);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	}
	
	@Test
	public void test_getLibraries()
	{
		Response response = RestAssured.get(API_ROOT);
		JsonPath jsonPathValidator = response.jsonPath();
		assertNotNull(jsonPathValidator.get("name").toString());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}
	
	@Test
	public void test_createbook_success() 
	{
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(createBook()).post((API_ROOT+"/A001/book"));
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

}
