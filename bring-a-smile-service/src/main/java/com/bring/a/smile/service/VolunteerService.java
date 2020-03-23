package com.bring.a.smile.service;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.dao.AuthorizationDao;
import com.bring.a.smile.dao.VolunteerDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.MessageResponse;
import com.bring.a.smile.model.UserAuthorization;
import com.bring.a.smile.model.UserType;
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

    public String register(Volunteer volunteer) throws DuplicateEntryException {
        String volunteerId = volunteerDao.register(volunteer);
        authorizationDao.register(new UserAuthorization(volunteerId, UserType.VOLUNTEER, volunteer.getPasswordHash()));
        log.info("Registered Volunteer :"+ volunteerId);
        return volunteerId;

    }
}
