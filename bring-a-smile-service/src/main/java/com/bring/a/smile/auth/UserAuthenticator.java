package com.bring.a.smile.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class UserAuthenticator implements Authenticator<BasicCredentials, User> {


    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (credentials.getUsername().equals("user")){
            return Optional.of(new User("user",UserType.USER));
        }
        if (credentials.getUsername().equals("coordinator")){
            return Optional.of(new User("coordinator",UserType.COORDINATOR));
        }
        if (credentials.getUsername().equals("admin")){
            return Optional.of(new User("admin",UserType.ADMIN));
        }
        return Optional.empty();
    }
}
