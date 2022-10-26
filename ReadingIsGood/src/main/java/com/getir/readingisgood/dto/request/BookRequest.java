package com.getir.readingisgood.dto.request;

import javax.validation.constraints.NotBlank;

public class BookRequest {

	private Long id;
	@NotBlank
	private String isbn;
	@NotBlank
    private String title;
	@NotBlank
    private String author;
    
    private Integer quantity;
        
    private Double price;

	public BookRequest() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookRequest [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
    
}
