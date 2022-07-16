package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.VendorMapper;
import com.fowox.restfruitshop.api.v1.model.VendorDTO;
import com.fowox.restfruitshop.controller.v1.VendorController;
import com.fowox.restfruitshop.domain.Vendor;
import com.fowox.restfruitshop.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(this::vendorToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorByID(Long id) {
        return vendorMapper.vendorToVendorDTO(
                vendorRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new)
        );
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = saveDTO(vendorDTO);
        return vendorToDTO(savedVendor);
    }

    @Override
    public VendorDTO updateVendor(VendorDTO vendorDTO, Long id) {
        vendorDTO.setId(id);
        Vendor savedVendor = saveDTO(vendorDTO);
        return vendorToDTO(savedVendor);
    }

    @Override
    public VendorDTO patchVendor(VendorDTO vendorDTO, Long id) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        vendorDTO.setId(id);
        if (vendorDTO.getName() == null) {
            vendorDTO.setName(vendor.getName());
        }
        Vendor savedVendor = saveDTO(vendorDTO);
        return vendorToDTO(savedVendor);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO vendorToDTO(Vendor vendor) {
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + vendorDTO.getId());

        return vendorDTO;
    }

    private Vendor saveDTO(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        return vendorRepository.save(vendor);
    }
}
