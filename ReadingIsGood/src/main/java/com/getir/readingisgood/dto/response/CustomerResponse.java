package com.getir.readingisgood.dto.response;

import java.util.List;

import com.getir.readingisgood.entity.Address;

public record CustomerResponse(
		
		 Long id,
	     String firstName,
	     String lastName,
	     String email,
	     String phone,
	     List<Address> addresses) {

}
