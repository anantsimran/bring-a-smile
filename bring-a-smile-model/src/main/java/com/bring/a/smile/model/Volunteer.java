package com.bring.a.smile.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Volunteer extends User {
    public Volunteer(String id, String name, String emailId, String contactNo, Address address, String passwordHash) {
        super(id, name, emailId, contactNo, address, passwordHash);
    }
}
