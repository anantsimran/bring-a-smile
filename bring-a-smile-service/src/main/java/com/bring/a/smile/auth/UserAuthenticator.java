package com.bring.a.smile.auth;

import com.bring.a.smile.dao.AuthorizationDao;
import com.bring.a.smile.model.UserAuthorization;
import com.bring.a.smile.model.UserType;
import com.google.inject.Inject;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class UserAuthenticator implements Authenticator<BasicCredentials, User> {

    private AuthorizationDao authorizationDao;


    @Inject
    public UserAuthenticator(AuthorizationDao authorizationDao) {
        this.authorizationDao = authorizationDao;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {

        Optional<UserAuthorization> userAuthorization = authorizationDao.getAuthorization(credentials.getUsername(), credentials.getPassword());

        if(userAuthorization.isPresent()){
            return Optional.of(new User(userAuthorization.get().getUserId(), userAuthorization.get().getUserType()));
        }

        return Optional.empty();
    }
}
