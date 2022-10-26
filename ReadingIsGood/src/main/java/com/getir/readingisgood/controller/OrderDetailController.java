package com.getir.readingisgood.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.getir.readingisgood.dto.request.OrderDetailRequest;
import com.getir.readingisgood.dto.response.OrderDetailResponse;
import com.getir.readingisgood.service.OrderDetailService;

@RestController
@RequestScope
@RequestMapping("/orders")
@CrossOrigin
@Validated
public class OrderDetailController {

	OrderDetailService orderDetailService;

	public OrderDetailController(OrderDetailService orderDetailService) {
		super();
		this.orderDetailService = orderDetailService;
	}
	@PostMapping
	public OrderDetailResponse addOrder(@RequestBody OrderDetailRequest orderDetail) {
		return orderDetailService.newOrderDetail(orderDetail);
	}
	
	@GetMapping("{id}")
	public OrderDetailResponse findByIsbn(@PathVariable Long id) {
		return orderDetailService.viewOrderDetails(id);
	}
	
	@GetMapping("{customerId}")
	public List<OrderDetailResponse> findCustomersOrderDetail(@PathVariable Long customerId) {
		return orderDetailService.findCustomersOrderDetails(customerId);
	}
	
	@GetMapping
	public Collection<OrderDetailResponse> findAllBooks(
			@RequestParam(required = false, defaultValue = "0") @Min(0) int pageNo,
			@RequestParam(required = false, defaultValue = "10") @Max(25) int pageSize) {
		return orderDetailService.findAllOrderDetails(pageNo, pageSize);
	}

}
