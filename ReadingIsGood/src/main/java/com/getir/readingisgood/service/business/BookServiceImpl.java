package com.getir.readingisgood.service.business;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.dto.request.BookRequest;
import com.getir.readingisgood.dto.response.BookResponse;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.exception.RestExceptionBase;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	private ModelMapper modelMapper;
	

	public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
		super();
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public BookResponse findBookByIsbn(String isbn) {
		return modelMapper.map(
				bookRepository.findByIsbn(isbn).orElseThrow( () -> new RestExceptionBase("Cannot find the book!", "unknown.book", "1")), 
				BookResponse.class
		);
	}

	@Override
	@Transactional
	public BookResponse deleteBook(String isbn) {
		var managedBook = bookRepository.findByIsbn(isbn).orElseThrow(() -> new RestExceptionBase("Cannot delete the book!", "unknown.book", "2"));
		bookRepository.delete(managedBook);
		return modelMapper.map(managedBook, BookResponse.class);

	}

	@Override
	public Collection<BookResponse> findAll(int pageNo, int pageSize) {
		return bookRepository.findAll(PageRequest.of(pageNo, pageSize))
                .getContent()
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
	}

	@Override
	@Transactional
	public BookResponse addBook(BookRequest book) {
		try {
			var managedBook = bookRepository.save(modelMapper.map(book,Book.class));
			return modelMapper.map(managedBook, BookResponse.class);
		} catch (Exception e) {
			System.err.println("Error has occured: " + e.getMessage());
			throw new RestExceptionBase("Cannot insert book!", "duplicate.isbn", "3");
		}
	}

	@Override
	@Transactional
	public BookResponse updateBook(BookRequest book) {
		String isbn = book.getIsbn();
		var managedBook = bookRepository.findByIsbn(isbn)
				                               .orElseThrow(() -> new RestExceptionBase("Cannot find the book!", "unknown.book", "4"));
		managedBook.setPrice(book.getPrice());
		managedBook.setQuantity(book.getQuantity());
		
		bookRepository.save(managedBook);
		return modelMapper.map(managedBook, BookResponse.class);
	}

}
