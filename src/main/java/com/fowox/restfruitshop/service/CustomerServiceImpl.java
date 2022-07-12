package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.CustomerMapper;
import com.fowox.restfruitshop.api.v1.model.CustomerDTO;
import com.fowox.restfruitshop.domain.Customer;
import com.fowox.restfruitshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static final String CUSTOMERS_URL = "/api/v1/customers/";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl(CUSTOMERS_URL + customerDTO.getId());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(
                customerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = saveDTO(customerDTO);
        return customerToDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long id) {
        customerDTO.setId(id);
        Customer savedCustomer = saveDTO(customerDTO);
        return customerToDTO(savedCustomer);
    }

    private CustomerDTO customerToDTO(Customer customer) {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(CUSTOMERS_URL + customerDTO.getId());

        return customerDTO;
    }

    private Customer saveDTO(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return customerRepository.save(customer);
    }

}
