package com.getir.readingisgood.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgood.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	 Optional<Book> findByIsbn(String isbn);

}
