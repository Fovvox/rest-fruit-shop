package com.fowox.restfruitshop.service;

import com.fowox.restfruitshop.api.v1.mapper.VendorMapper;
import com.fowox.restfruitshop.api.v1.model.VendorDTO;
import com.fowox.restfruitshop.domain.Vendor;
import com.fowox.restfruitshop.repository.VendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class VendorServiceTest {
    public static final String NAME = "lastName";
    public static final long ID = 2L;

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendorList = Arrays.asList(new Vendor(), new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendorList);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(4, vendorDTOS.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorByID(2L);

        assertEquals(ID, vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    void createNewVendor() {
        VendorDTO vendor = new VendorDTO();
        vendor.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(ID);
        savedVendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendor);

        assertEquals(vendor.getName(), savedDTO.getName());
        assertEquals("/api/v1/vendors/2", savedDTO.getVendorUrl());

    }

    @Test
    void updateVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor Co.");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.updateVendor(vendorDTO, 1L);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals("/api/v1/vendors/1", savedDto.getVendorUrl());

    }
}
