package com.getir.readingisgood.controller;

import java.util.Collection;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.getir.readingisgood.dto.request.BookRequest;
import com.getir.readingisgood.dto.response.BookResponse;
import com.getir.readingisgood.exception.ErrorMessage;
import com.getir.readingisgood.exception.RestExceptionBase;
import com.getir.readingisgood.service.BookService;

import io.swagger.annotations.Api;


@RestController
@RequestScope
@RequestMapping("/books")
@CrossOrigin
@Validated
@Api(value = "Book Api documentation")
public class BookController {
	
	BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	@GetMapping("{isbn}")
	public BookResponse findByIsbn(@PathVariable String isbn) {
		return bookService.findBookByIsbn(isbn);
	}

	@DeleteMapping("{isbn}")
	public BookResponse deleteByIsbn(@PathVariable String isbn) {
		return bookService.deleteBook(isbn);
	}
	@GetMapping
	public Collection<BookResponse> findAllBooks(
			@RequestParam(required = false, defaultValue = "0") @Min(0) int pageNo,
			@RequestParam(required = false, defaultValue = "10") @Max(25) int pageSize) {
		return bookService.findAll(pageNo, pageSize);
	}

	@PostMapping
	public BookResponse addBook(@RequestBody BookRequest book) {
		return bookService.addBook(book);
	}


	@PutMapping("{isbn}")
	public BookResponse updateBook(@PathVariable String isbn, @RequestBody BookRequest book) {
		return bookService.updateBook(book);
	}

	@ExceptionHandler({ RestExceptionBase.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleErrors(RestExceptionBase e) {
		return new ErrorMessage(e.getMessageId(), e.getDebugId(), e.getMessage());
	}

	

}
