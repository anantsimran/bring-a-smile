package com.bring.a.smile.service;

import com.bring.a.smile.auth.User;
import com.bring.a.smile.model.MessageResponse;

public class CoordinatorService {

    public MessageResponse getLoginMessage(User user) {
        return new MessageResponse(user + " is logged in");
    }
}
