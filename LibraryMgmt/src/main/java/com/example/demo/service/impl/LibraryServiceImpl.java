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

import com.example.demo.entity.Library;
import com.example.demo.exception.LibraryNotFoundException;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.service.LibraryService;
import com.google.common.base.Optional;

/**
 * @author Gokul
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService {
	
	Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Override
	public List<Library> getLibraries() {
		// TODO Auto-generated method stub
		logger.info("getLibraries() from LibraryService");
		List<Library> libList = new ArrayList<Library>();
		//libraryRepository.findAll().forEach(libList::add);
		libList.addAll(libraryRepository.findAll());
		return libList;
	}

	@Override
	@Transactional
	public void saveLibrary(Library library) {
		// TODO Auto-generated method stub
		try {
			libraryRepository.save(library);
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Library details saved successfully");
	}

	@Override
	public Library findByLibraryNum(String libNum) {
		// TODO Auto-generated method stub
		Optional<Library> library = Optional.fromNullable(libraryRepository.findByLibraryNum(libNum));
		if(!library.isPresent())
			throw new LibraryNotFoundException("Library not found");
		
		return library.get();
	}

}
