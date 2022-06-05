package com.fowox.restfruitshop.api.v1.mapper;

import com.fowox.restfruitshop.api.v1.model.CustomerDTO;
import com.fowox.restfruitshop.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
