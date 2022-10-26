package com.getir.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgood.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
