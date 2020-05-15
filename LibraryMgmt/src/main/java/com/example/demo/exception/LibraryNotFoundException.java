/**
 * 
 */
package com.example.demo.exception;

/**
 * @author Gokul
 *
 */
public class LibraryNotFoundException extends RuntimeException {
	
	public LibraryNotFoundException(String message) {
        super(message);
    }

}
