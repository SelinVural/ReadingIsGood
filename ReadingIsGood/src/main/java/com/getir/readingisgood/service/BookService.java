package com.getir.readingisgood.service;

import java.util.Collection;

import com.getir.readingisgood.dto.request.BookRequest;
import com.getir.readingisgood.dto.response.BookResponse;

public interface BookService {
	
	BookResponse findBookByIsbn(String isbn);
	
	BookResponse deleteBook(String isbn);
	
	Collection<BookResponse> findAll(int pageNo, int pageSize);
	
	BookResponse addBook(BookRequest book);
	
	BookResponse updateBook(BookRequest book);
	
	

}
