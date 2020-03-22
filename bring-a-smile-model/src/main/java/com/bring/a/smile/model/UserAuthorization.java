package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserAuthorization {
    private String userId;
    private UserType userType;
    private String passwordHash;
}
