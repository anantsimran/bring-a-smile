package com.bring.a.smile.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {
    private String streetAddress;
    private String pincode;
}
