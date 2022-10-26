package com.getir.readingisgood.config;

import java.util.ArrayList;
import java.util.Objects;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.getir.readingisgood.dto.request.BookRequest;
import com.getir.readingisgood.dto.request.CustomerRequest;
import com.getir.readingisgood.dto.request.OrderDetailRequest;
import com.getir.readingisgood.dto.response.BookResponse;
import com.getir.readingisgood.dto.response.CustomerResponse;
import com.getir.readingisgood.dto.response.OrderDetailResponse;
import com.getir.readingisgood.entity.Address;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.OrderDetail;

@Configuration
public class ModelMapperConfig {

	private static final Converter<Book,BookResponse>
	BOOK_TO_BOOK_RESPONSE_CONVERTER =
		context -> new BookResponse(
				context.getSource().getId(),
				context.getSource().getIsbn(),
				context.getSource().getTitle(),
				context.getSource().getAuthor(),
				context.getSource().getQuantity(),
				context.getSource().getPrice());
		
	private static final Converter<Customer,CustomerResponse>
	CUSTOMER_TO_CUSTOMER_RESPONSE_CONVERTER =
		context -> new CustomerResponse(
				context.getSource().getId(),
				context.getSource().getFirstName(),
				context.getSource().getLastName(),
				context.getSource().getEmail(),
				context.getSource().getPhone(),
				context.getSource().getAddresses());
		
	private static final Converter<OrderDetail,OrderDetailResponse>
	ORDERDETAIL_TO_ORDERDETAIL_RESPONSE_CONVERTER =
		context -> new OrderDetailResponse(
				context.getSource().getId(),
				context.getSource().getCustomer().getId(),
				context.getSource().getTotalPrice(),
				context.getSource().getBooks().stream().map(book -> book.getId()).toList(),				 
				context.getSource().getAddress().getId());
			

		
		private static final Converter<BookRequest, Book> 
		BOOK_REQUEST_TO_BOOK_CONVERTER =
				context -> new Book(
						context.getSource().getId(),
						context.getSource().getIsbn(), 
						context.getSource().getTitle(),
						context.getSource().getAuthor(), 
						context.getSource().getQuantity(),
						context.getSource().getPrice());

	private static final Converter<CustomerRequest,Customer>
	CUSTOMER_REQUEST_TO_CUSTOMER_CONVERTER = 
	(context) ->  new Customer(
			context.getSource().getId(),
			context.getSource().getFirstName(),
			context.getSource().getLastName(),
			context.getSource().getEmail(),
			context.getSource().getPhone(),
			context.getSource().getAddresses());
	
			
	private static final Converter<OrderDetailRequest,OrderDetail>
	ORDERDETAIL_REQUEST_TO_ORDERDETAIL_CONVERTER = 
	(context) -> {
		var orderDetail = context.getDestination();
		var orderDetailReq = context.getSource();
		
		if (Objects.nonNull(orderDetailReq.getId()))
			orderDetail.setId(orderDetailReq.getId());
		
		if (Objects.nonNull(orderDetailReq.getCustomerId())) {
			var cust= new Customer();
			cust.setId(orderDetailReq.getCustomerId());
			orderDetail.setCustomer(cust);
		}
		if (Objects.nonNull(orderDetailReq.getTotalPrice()))
			orderDetail.setTotalPrice(orderDetailReq.getTotalPrice());	
		
		if (Objects.nonNull(orderDetailReq.getAddressId())) {
			var address= new Address();
			address.setId(orderDetailReq.getAddressId());
			orderDetail.setAddress(address);
		}
		if (Objects.nonNull(orderDetailReq.getBookIds())) {
			
			var books = orderDetailReq.getBookIds()
					.stream()
					.map( bookId  -> {
						var bk = new Book();
						bk.setId(bookId);
						return bk;
					}).toList();
			orderDetail.setBooks(new ArrayList<>(books));		
		}
	
		return orderDetail;	
	};

	    
	

	@Bean("standardModelMapper") 
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper(); 
		
		
		
		modelMapper.addConverter(BOOK_TO_BOOK_RESPONSE_CONVERTER,Book.class, BookResponse.class);
		modelMapper.addConverter(CUSTOMER_TO_CUSTOMER_RESPONSE_CONVERTER,Customer.class, CustomerResponse.class);
		modelMapper.addConverter(ORDERDETAIL_TO_ORDERDETAIL_RESPONSE_CONVERTER,OrderDetail.class, OrderDetailResponse.class);
		
		modelMapper.addConverter(BOOK_REQUEST_TO_BOOK_CONVERTER ,BookRequest.class, Book.class);
		modelMapper.addConverter(CUSTOMER_REQUEST_TO_CUSTOMER_CONVERTER ,CustomerRequest.class, Customer.class );
		modelMapper.addConverter(ORDERDETAIL_REQUEST_TO_ORDERDETAIL_CONVERTER ,OrderDetailRequest.class, OrderDetail.class );
		
		return modelMapper;
	}
}