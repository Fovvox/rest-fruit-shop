package com.fowox.restfruitshop.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {
    private Long id;
    private String name;
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
