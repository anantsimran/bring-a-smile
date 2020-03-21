package com.bring.a.smile.service;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.model.MessageResponse;
import com.bring.a.smile.model.Volunteer;

public class VolunteerService {
    public MessageResponse getLoginMessage(User user) {
        return new MessageResponse(user + " is logged in");
    }

    public String register(Volunteer volunteer) {
        return "7890";
    }
}
