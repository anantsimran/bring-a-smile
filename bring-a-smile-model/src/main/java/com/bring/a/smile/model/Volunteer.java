package com.bring.a.smile.model;

public class Volunteer extends User {
    public Volunteer(String id, String name, String emailId, String contactNo, Address address, String passwordHash) {
        super(id, name, emailId, contactNo, address, passwordHash);
    }
}
