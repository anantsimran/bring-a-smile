package com.bring.a.smile.model;



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
