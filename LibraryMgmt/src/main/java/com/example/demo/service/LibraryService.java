/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Library;

/**
 * @author Gokul
 *
 */
public interface LibraryService {

	public List<Library> getLibraries();
	
	public void saveLibrary(Library library);
	
	public Library findByLibraryNum(String libNum);
}
