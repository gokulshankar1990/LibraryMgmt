/**
 * 
 */
package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.google.common.base.Optional;

/**
 * @author Gokul
 *
 */
@Service
public class BookServiceImpl implements BookService {
	
Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	LibraryService libraryService;

	@Override
	@Transactional
	public void createBook(String libNum, Book book) {
		// TODO Auto-generated method stub
			Library library = libraryService.findByLibraryNum(libNum);
			logger.info("Library ID @@@ "+library.getLibId());
			book.setLibrary(library);
			bookRepository.save(book);
		logger.info("Book details saved successfully");
	}

	@Override
	public List<Book> getBooksFromLibraryAddress(String libNum) {
		// TODO Auto-generated method stub
		logger.info("getBooksFromLibraryAddress called from BookService");
		List<Book> booksFromAddress = new ArrayList<Book>();
		bookRepository.findBooksForAddress(libNum).forEach(booksFromAddress::add);
		return booksFromAddress;
	}

	@Override
	@Transactional
	public void updateBook(String libNum, String isbn, Book book) {
		// TODO Auto-generated method stub
		logger.info("updateBook called from BookService");
			Book bookFromDb = getBookByISBNAndLibNum(isbn,libNum);
			bookFromDb.setBookName(book.getBookName());
			bookFromDb.setPublisher(book.getPublisher());
			if(book.getLibrary()!=null) {
				Library library = libraryService.findByLibraryNum(book.getLibrary().getLibraryNum());
				logger.info("Library ID @@@ "+library.getLibId());
				bookFromDb.setLibrary(library);
			}
			bookRepository.save(bookFromDb);
		logger.info("Book details updated successfully");
	}

	@Override
	@Transactional
	public void deleteBook(String libNum, String isbn) {
		// TODO Auto-generated method stub
		logger.info("deleteBook called from BookService");
			Book bookFromDb = getBookByISBNAndLibNum(isbn,libNum);
			logger.info(bookFromDb.getIsbn());
			bookRepository.delete(bookFromDb);
	}

	@Override
	public Book getBookByISBNAndLibNum(String isbn,String libNum) {
		// TODO Auto-generated method stub
		logger.info("Fetch book BookService");
		Optional<Book> book = Optional.fromNullable(bookRepository.findByIsbnAndLibNum(isbn,libNum));
		if(!book.isPresent())
			throw new BookNotFoundException("Book not found");
		
		return book.get();
	}

}
