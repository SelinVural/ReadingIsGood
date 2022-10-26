package com.getir.readingisgood.service;

import java.util.Collection;
import java.util.List;

import com.getir.readingisgood.dto.request.OrderDetailRequest;
import com.getir.readingisgood.dto.response.OrderDetailResponse;

public interface OrderDetailService {
	
	 
	OrderDetailResponse newOrderDetail(OrderDetailRequest OrderDetail);
	
	OrderDetailResponse viewOrderDetails(Long id);
	
	List<OrderDetailResponse> findCustomersOrderDetails(Long customerId);
	
	Collection<OrderDetailResponse> findAllOrderDetails(int pageNo, int pageSize);

	

}
