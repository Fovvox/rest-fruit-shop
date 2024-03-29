package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.CustomerMapper;
import com.fowox.restfruitshop.api.v1.model.CustomerDTO;
import com.fowox.restfruitshop.controller.v1.CustomerController;
import com.fowox.restfruitshop.domain.Customer;
import com.fowox.restfruitshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return customerRepository.findAll().stream()
                .map(this::customerToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(
                customerRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new));
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

    @Override
    public CustomerDTO patchCustomer(CustomerDTO customerDTO, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        customerDTO.setId(customer.getId());
        if (customerDTO.getLastName() == null) {
            customerDTO.setLastName(customer.getLastName());
        }
        if (customerDTO.getFirstName() == null) {
            customerDTO.setFirstName(customer.getFirstName());
        }
        Customer savedCustomer = saveDTO(customerDTO);
        return customerToDTO(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO customerToDTO(Customer customer) {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(CustomerController.BASE_URL + customerDTO.getId());

        return customerDTO;
    }

    private Customer saveDTO(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return customerRepository.save(customer);
    }

}
