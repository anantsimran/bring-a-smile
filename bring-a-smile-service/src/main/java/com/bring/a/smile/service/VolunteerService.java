package com.bring.a.smile.service;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.dao.AuthorizationDao;
import com.bring.a.smile.dao.VolunteerDao;
import com.bring.a.smile.model.MessageResponse;
import com.bring.a.smile.model.Volunteer;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VolunteerService {

    private VolunteerDao volunteerDao;
    private AuthorizationDao authorizationDao;

    @Inject
    public VolunteerService(VolunteerDao volunteerDao, AuthorizationDao authorizationDao) {
        this.volunteerDao = volunteerDao;
        this.authorizationDao = authorizationDao;
    }

    public MessageResponse getLoginMessage(User user) {
        return new MessageResponse(user + " is logged in");
    }

    public String register(Volunteer volunteer) {
        String volunteerId = volunteerDao.register(volunteer);
        log.info("Registered Volunteer :"+ volunteerId);
        return volunteerId;

    }
}
