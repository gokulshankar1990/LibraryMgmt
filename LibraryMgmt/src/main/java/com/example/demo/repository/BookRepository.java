/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

/**
 * @author Gokul
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b JOIN FETCH b.library WHERE b.library.libraryNum = ?1")
	public List<Book> findBooksForAddress(String libNum);

	@Query("SELECT b FROM Book b JOIN FETCH b.library WHERE b.isbn LIKE %:name% AND b.library.libraryNum = :libNum")
	public Book findByIsbnAndLibNum(@Param("name") String isbn,@Param("libNum") String libNum);
	
}
