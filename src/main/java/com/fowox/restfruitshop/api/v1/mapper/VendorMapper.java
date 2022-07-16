package com.fowox.restfruitshop.api.v1.mapper;

import com.fowox.restfruitshop.api.v1.model.VendorDTO;
import com.fowox.restfruitshop.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
