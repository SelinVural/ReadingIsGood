package com.getir.readingisgood.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class OrderDetailRequest {
	
    private Long id;
    @NotBlank
    Long customerId;
    
    Double totalPrice;

    //private List<Book> books = new ArrayList<>();
    private List<Long> bookIds = new ArrayList<>();
    
    private Long addressId;

	public OrderDetailRequest() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "OrderDetailRequest [id=" + id + ", customerId=" + customerId + ", totalPrice=" + totalPrice
				+ ", bookId=" + bookIds + ", addressId=" + addressId + "]";
	}

	
    
    
    

}
