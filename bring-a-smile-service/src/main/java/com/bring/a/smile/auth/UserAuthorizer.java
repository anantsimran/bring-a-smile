package com.bring.a.smile.auth;

import io.dropwizard.auth.Authorizer;

public class UserAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User principal, String role) {
        return principal.getUserType().name().equals(role);
    }
}
