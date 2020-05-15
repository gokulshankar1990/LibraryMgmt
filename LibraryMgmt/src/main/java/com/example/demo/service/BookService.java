/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Book;

/**
 * @author Gokul
 *
 */
public interface BookService {

	public void createBook(String libNum,Book book);
	
	public List<Book> getBooksFromLibraryAddress(String libNum);
	
	public void updateBook(String libNum,String isbn,Book book);
	
	public void deleteBook(String libNum,String isbn);
	
	public Book getBookByISBNAndLibNum(String isbn,String libNum);
}
