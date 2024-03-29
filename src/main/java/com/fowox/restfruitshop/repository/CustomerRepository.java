package com.fowox.restfruitshop.repository;

import com.fowox.restfruitshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long id);

    @Override
    <S extends Customer> S save(S entity);
}
