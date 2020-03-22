package com.bring.a.smile.auth;


import com.bring.a.smile.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;

@Data
@AllArgsConstructor
public class User implements Principal {

    private String id;
    private UserType userType;

    @Override
    public String getName() {
        return id;
    }
}
