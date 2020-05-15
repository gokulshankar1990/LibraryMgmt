/**
 * 
 */
package com.example.demo.exceptionhandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.LibraryNotFoundException;

/**
 * @author Gokul
 *
 */
@ControllerAdvice
public class LibraryMgmtExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler({ BookNotFoundException.class })
    protected ResponseEntity<Object> handleBookNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Book not found", 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({ LibraryNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Library not found", 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler({ 
	      ConstraintViolationException.class, 
	      DataIntegrityViolationException.class })
	    public ResponseEntity<Object> handleBadRequest(
	      Exception ex, WebRequest request) {
	        return handleExceptionInternal(ex, "Request data is not valid", 
	          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
}
