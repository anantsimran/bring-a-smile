package com.bring.a.smile.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Coordinator extends User {

    public Coordinator(String id, String name, String emailId, String contactNo, Address address, String passwordHash) {
        super(id, name, emailId, contactNo, address, passwordHash);
    }
}
