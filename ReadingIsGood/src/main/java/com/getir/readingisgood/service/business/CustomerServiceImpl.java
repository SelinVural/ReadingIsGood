package com.getir.readingisgood.service.business;

import java.util.Collection;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.getir.readingisgood.dto.request.CustomerRequest;
import com.getir.readingisgood.dto.response.CustomerResponse;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.exception.RestExceptionBase;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;
	

	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
		super();
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CustomerResponse newCustomer(CustomerRequest customer) {
		try {
			var managedCustomer = customerRepository.save(modelMapper.map(customer,Customer.class));
			return modelMapper.map(managedCustomer, CustomerResponse.class);
		} catch (Exception e) {
			System.err.println("Error has occured: " + e.getMessage());
			throw new RestExceptionBase("Cannot insert customer!", "duplicate.customer", "3");
		}
	}

	@Override
	public CustomerResponse findCustomerById(Long id) {
		return modelMapper.map(
				customerRepository.findById(id).orElseThrow( () -> new RestExceptionBase("Cannot customer the book!", "unknown.customer", "1")), 
				CustomerResponse.class
		);
	}

	@Override
	@Transactional
	public CustomerResponse deleteCustomer(Long id) {
		var managedCustomer = customerRepository.findById(id).orElseThrow(() -> new RestExceptionBase("Cannot delete the customer!", "unknown.customer", "2"));
		customerRepository.delete(managedCustomer);
		return modelMapper.map(managedCustomer, CustomerResponse.class);
	}

	@Override
	public Collection<CustomerResponse> findAll(int pageNo, int pageSize) {
		return customerRepository.findAll(PageRequest.of(pageNo, pageSize))
                .getContent()
                .stream()
                .map(book -> modelMapper.map(book, CustomerResponse.class))
                .toList();
	}

	@Override
	public CustomerResponse updateCustomer(CustomerRequest customer) {
		Long customerId = customer.getId();
		var managedCustomer = customerRepository.findById(customerId)
				                               .orElseThrow(() -> new RestExceptionBase("Cannot find the book!", "unknown.book", "4"));
		managedCustomer.setAddresses(customer.getAddresses());
		managedCustomer.setEmail(customer.getEmail());
		managedCustomer.setPhone(customer.getPhone());
		customerRepository.save(managedCustomer);
		return modelMapper.map(managedCustomer, CustomerResponse.class);
	}

}
