package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.CustomerMapper;
import com.fowox.restfruitshop.api.v1.model.CustomerDTO;
import com.fowox.restfruitshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        ArrayList<CustomerDTO> result = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> result.add(customerMapper.customerToCustomerDTO(customer)));
        return result;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(customerRepository.getById(id));
    }
}
