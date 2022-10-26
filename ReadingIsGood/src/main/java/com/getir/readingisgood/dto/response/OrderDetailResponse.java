package com.getir.readingisgood.dto.response;

import java.util.List;

public record OrderDetailResponse(
		Long id,

	    Long customerId,
	    
	    Double totalPrice,

	    List<Long> bookId ,
	    
	    Long addressId
		) {

}
