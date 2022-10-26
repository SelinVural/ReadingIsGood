
package com.getir.readingisgood.service.business;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.getir.readingisgood.dto.request.OrderDetailRequest;
import com.getir.readingisgood.dto.response.OrderDetailResponse;
import com.getir.readingisgood.entity.OrderDetail;
import com.getir.readingisgood.exception.RestExceptionBase;
import com.getir.readingisgood.repository.OrderDetailRepository;
import com.getir.readingisgood.service.OrderDetailService;


@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	private OrderDetailRepository orderDetailRepository;
	private ModelMapper modelMapper;
	

	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ModelMapper modelMapper) {
		super();
		this.orderDetailRepository = orderDetailRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public OrderDetailResponse newOrderDetail(OrderDetailRequest orderDetail) {
		try {
			var managedOrderDetail = orderDetailRepository.save(modelMapper.map(orderDetail,OrderDetail.class));
			return modelMapper.map(managedOrderDetail, OrderDetailResponse.class);
		} catch (Exception e) {
			System.err.println("Error has occured: " + e.getMessage());
			throw new RestExceptionBase("Cannot insert order!", "duplicate.order", "3");
		}
	}

	@Override
	public OrderDetailResponse viewOrderDetails(Long id) {
		return modelMapper.map(
				orderDetailRepository.findById(id).orElseThrow( () -> new RestExceptionBase("Cannot find the order!", "unknown.order", "1")), 
				OrderDetailResponse.class
		);
	}

	@Override
	public List<OrderDetailResponse> findCustomersOrderDetails(Long customerId) {		
		try {
			var orders = orderDetailRepository.findByCustomerId(customerId)
					.stream()
					.map(orderDetail -> modelMapper.map(orderDetail, OrderDetailResponse.class))
					.toList();	

			return orders;
		} catch(Exception e) {
			System.err.println("Error has occured: " + e.getMessage());
			throw new RestExceptionBase("Cannot find orders!", "unknown.order", "4");
		}			
	}
	
	@Override
	public Collection<OrderDetailResponse> findAllOrderDetails(int pageNo, int pageSize) {
		try {
			var orders = orderDetailRepository.findAll(PageRequest.of(pageNo, pageSize))
					.getContent()
					.stream()
					.map(orderDetail -> modelMapper.map(orderDetail, OrderDetailResponse.class))
					.toList();	

			return orders;
		} catch(Exception e) {
			System.err.println("Error has occured: " + e.getMessage());
			throw new RestExceptionBase("Cannot find orders!", "unknown.order", "4");
		}
	}
	

}
