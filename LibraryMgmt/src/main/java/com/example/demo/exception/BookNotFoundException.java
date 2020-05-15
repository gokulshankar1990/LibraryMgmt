/**
 * 
 */
package com.example.demo.exception;

/**
 * @author Gokul
 *
 */
public class BookNotFoundException extends RuntimeException {
	
	public BookNotFoundException(String message) {
        super(message);
    }

}
