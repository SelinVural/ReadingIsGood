package com.getir.readingisgood.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgood.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	
	Optional<OrderDetail> findByCustomerId(Long customerId);

}
