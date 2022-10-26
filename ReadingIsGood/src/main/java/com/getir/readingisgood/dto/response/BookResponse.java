package com.getir.readingisgood.dto.response;

public record BookResponse(
		 Long id,
	
		 String isbn,
		
	     String title,
		
	     String author,
	    
	     Integer quantity,
	        
	     Double price) {
}
