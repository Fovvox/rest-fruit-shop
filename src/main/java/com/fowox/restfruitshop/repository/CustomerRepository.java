package com.fowox.restfruitshop.repository;

import com.fowox.restfruitshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Override
    Customer getById(Long id);
}
