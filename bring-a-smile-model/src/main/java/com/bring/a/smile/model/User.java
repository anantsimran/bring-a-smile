package com.bring.a.smile.model;


//TODO: Add mechanism for verification

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private String id;
    private String name;
    private String emailId;
    private String contactNo;
    private Address address;
    private String passwordHash;
}
