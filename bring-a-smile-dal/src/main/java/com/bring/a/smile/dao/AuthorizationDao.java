package com.bring.a.smile.dao;

import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.UserAuthorization;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;


@Slf4j
public class AuthorizationDao {

    private static String namespace = "auth";


    private IESDao esDao;
    private IESSearchDao esSearchDao;
    private ESUtils esUtils;

    public AuthorizationDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils) {
        this.esDao = esDao;
        this.esSearchDao = esSearchDao;
        this.esUtils = esUtils;
    }

    public Optional<UserAuthorization> getAuthorization(String username, String password) {
        Optional<String> userAuthorizationString = esDao.getDocument(namespace, username);
        if(userAuthorizationString.isPresent()){
            UserAuthorization userAuthorization;
            try {
                userAuthorization = esUtils.deserialize(userAuthorizationString.get(),
                        UserAuthorization.class);
            } catch (IOException e) {
                log.error("Could not deserialise Object " + userAuthorizationString, e);
                throw new RuntimeException("Could not deserialise Object " + userAuthorizationString, e);
            }
            if(userAuthorization.getPasswordHash().equals(password)){
                  return Optional.of(userAuthorization);
              }
              return Optional.empty();
        }
        return Optional.empty();
    }

    public void register(UserAuthorization userAuthorization) throws DuplicateEntryException {
        String userAuthorizationDocString;
        try {
            userAuthorizationDocString = esUtils.serialize(userAuthorization);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + userAuthorization, e);
            throw new RuntimeException("Could not serialise Object " + userAuthorization, e);
        }
        esDao.createDocument(namespace, userAuthorization.getUserId(),userAuthorizationDocString);
    }
}
