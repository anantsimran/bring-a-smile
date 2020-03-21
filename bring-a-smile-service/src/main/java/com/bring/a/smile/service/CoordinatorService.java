package com.bring.a.smile.service;

import com.bring.a.smile.model.Coordinator;
import com.bring.a.smile.model.MessageResponse;

public class CoordinatorService {

    public MessageResponse getLoginMessage(com.bring.a.smile.auth.User user) {
        return new MessageResponse(user + " is logged in");
    }

    public String registerCoordinator(Coordinator coordinator) {
        return "123";
    }
}
