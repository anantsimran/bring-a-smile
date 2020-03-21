package com.bring.a.smile.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Address {
    private String streetAddress;
    private String pincode;
}
