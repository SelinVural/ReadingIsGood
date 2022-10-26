package com.getir.readingisgood.service;

import java.util.Collection;

import com.getir.readingisgood.dto.request.CustomerRequest;
import com.getir.readingisgood.dto.response.CustomerResponse;



public interface CustomerService {
	

	CustomerResponse newCustomer(CustomerRequest customer);
	
	CustomerResponse findCustomerById(Long id);
	
	CustomerResponse deleteCustomer(Long id);
	
	Collection<CustomerResponse> findAll(int pageNo, int pageSize);
	
	CustomerResponse updateCustomer(CustomerRequest customer);
	
}
