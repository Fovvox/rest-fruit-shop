package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.CustomerMapper;
import com.fowox.restfruitshop.api.v1.model.CustomerDTO;
import com.fowox.restfruitshop.domain.Customer;
import com.fowox.restfruitshop.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final long ID = 2L;
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void getAllCustomers() {
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(4, customerDTOS.size());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(2L);

        assertEquals(ID, customerDTO.getId());
        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
    }
}