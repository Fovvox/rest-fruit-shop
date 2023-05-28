package com.fowox.restfruitshop.api.v1.mapper;

import com.fowox.restfruitshop.api.v1.model.VendorDTO;
import com.fowox.restfruitshop.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor_1");

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getId(), vendorDTO.getId());
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    void vendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(1L);
        vendorDTO.setName("vendor_1");

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertEquals(vendorDTO.getId(), vendor.getId());
        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}