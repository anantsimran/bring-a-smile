package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserAuthorization {
    private String userId;
    private UserType userType;
    private String passwordHash;
}
