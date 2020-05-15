/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Library;

/**
 * @author Gokul
 *
 */
@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
	
	public Library findByLibraryNum(String libNum);

}
