package com.fowox.restfruitshop.repository;

import com.fowox.restfruitshop.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
