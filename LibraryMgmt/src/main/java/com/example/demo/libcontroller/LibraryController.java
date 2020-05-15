/**
 * 
 */
package com.example.demo.libcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Gokul
 *
 */
@RestController
@Api(value = "Set of Library API")
@CrossOrigin
public class LibraryController {
	
	Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	LibraryService libraryService;
	
	@GetMapping(value = "/library")
	@ApiOperation(value = "Fetches the libraries available")
	public List<Library> getLibraries(){
		logger.info("getLibraries() called");
		return libraryService.getLibraries();
	}
	
	@PostMapping(value = "/library")
	@ApiOperation(value = "Create an library")
	public ResponseEntity<String> saveLibrary(@RequestBody Library library)
	{
		logger.info("saveLibrary called");
		libraryService.saveLibrary(library);
		return new ResponseEntity<String>("New Library created", HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/library/{libNum}/book")
	@ApiOperation(value = "Fetch the books corresponding to an library")
	public List<Book> getBooksFromLibraryAddress(@PathVariable String libNum)
	{
		logger.info("getBooksFromLibraryAddress called from LibraryController");
		return bookService.getBooksFromLibraryAddress(libNum);
	}
	
	@PostMapping(value = "/library/{libNum}/book")
	@ApiOperation(value = "create a book for a library")
	public ResponseEntity<String> createBook(@PathVariable String libNum, @RequestBody Book book) {
		logger.info("createBook called ");
		bookService.createBook(libNum, book);
		return new ResponseEntity<String>("New Book created for the library "+libNum, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/library/{libNum}/book/{isbn}")
	@ApiOperation(value = "Update a book for a library")
	public ResponseEntity<String> updateBook(@PathVariable String libNum,@PathVariable String isbn, @RequestBody Book book) {
		logger.info("updateBook called");
		bookService.updateBook(libNum, isbn, book);
		return new ResponseEntity<String>("The Book "+ isbn + " updated for the library "+libNum, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/library/{libNum}/book/{isbn}")
	@ApiOperation(value = "Delete a book from a library")
	public ResponseEntity<String> deleteBook(@PathVariable String libNum,@PathVariable String isbn) {
		logger.info("deleteBook called");
		bookService.deleteBook(libNum, isbn);
		return new ResponseEntity<String>("The Book "+ isbn + " deleted from library "+libNum, HttpStatus.OK);
	}
	
	@GetMapping(value = "/library/{libNum}/book/{isbn}")
	@ApiOperation(value = "Fetch the book corresponding to an isbn and a library")
	public Book getBookByISBN(@PathVariable String libNum,@PathVariable String isbn) {
		logger.info("getBookByISBN called");
		return bookService.getBookByISBNAndLibNum(isbn,libNum);
	}
}
