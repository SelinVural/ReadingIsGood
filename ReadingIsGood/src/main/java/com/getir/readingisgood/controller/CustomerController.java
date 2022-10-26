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

import com.getir.readingisgood.dto.request.CustomerRequest;
import com.getir.readingisgood.dto.response.CustomerResponse;
import com.getir.readingisgood.exception.ErrorMessage;
import com.getir.readingisgood.exception.RestExceptionBase;
import com.getir.readingisgood.service.CustomerService;

@RestController
@RequestScope
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CustomerController {
	
	CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping("{id}")
	public CustomerResponse findCustomerById(@PathVariable Long id) {
		return customerService.findCustomerById(id);
	}

	@DeleteMapping("{id}")
	public CustomerResponse deleteCustomerById(@PathVariable Long id) {
		return customerService.deleteCustomer(id);
	}
	@GetMapping
	public Collection<CustomerResponse> findAllCustomers(
			@RequestParam(required = false, defaultValue = "0") @Min(0) int pageNo,
			@RequestParam(required = false, defaultValue = "10") @Max(25) int pageSize) {
		return customerService.findAll(pageNo, pageSize);
	}

	@PostMapping
	public CustomerResponse addCustomer(@RequestBody CustomerRequest customer) {
		return customerService.newCustomer(customer);
	}

	@PutMapping("{id}")
	public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customer) {
		return customerService.updateCustomer(customer);
	}

	@ExceptionHandler({ RestExceptionBase.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleErrors(RestExceptionBase e) {
		return new ErrorMessage(e.getMessageId(), e.getDebugId(), e.getMessage());
	}
	

}
