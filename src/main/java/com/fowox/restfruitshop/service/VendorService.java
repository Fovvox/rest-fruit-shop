package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();

    VendorDTO getVendorByID(Long id);

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(VendorDTO vendorDTO, Long id);

    VendorDTO patchVendor(VendorDTO vendorDTO, Long id);

    void deleteVendor(Long id);
}
