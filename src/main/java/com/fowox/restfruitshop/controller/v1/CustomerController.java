package com.fowox.restfruitshop.controller.v1;


import com.fowox.restfruitshop.api.v1.model.CustomerDTO;
import com.fowox.restfruitshop.api.v1.model.CustomerListDTO;
import com.fowox.restfruitshop.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return new ResponseEntity<>(customerService.getCustomerById(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {

        customerDTO = customerService.createNewCustomer(customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.updateCustomer(customerDTO, Long.parseLong(id));
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.patchCustomer(customerDTO, Long.parseLong(id));
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
