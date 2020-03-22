package com.bring.a.smile.service;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.dao.VolunteerDao;
import com.bring.a.smile.model.MessageResponse;
import com.bring.a.smile.model.Volunteer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VolunteerService {

    private VolunteerDao volunteerDao;

    public VolunteerService(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
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
