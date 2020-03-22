package com.bring.a.smile.service;

import com.bring.a.smile.dao.CoordinatorDao;
import com.bring.a.smile.model.Coordinator;
import com.bring.a.smile.model.MessageResponse;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoordinatorService {

    private CoordinatorDao coordinatorDao;

    @Inject
    public CoordinatorService(CoordinatorDao coordinatorDao) {
        this.coordinatorDao = coordinatorDao;
    }

    public MessageResponse getLoginMessage(com.bring.a.smile.auth.User user) {
        return new MessageResponse(user + " is logged in");
    }

    public String registerCoordinator(Coordinator coordinator) {
        String coordinatorId=  coordinatorDao.registerCoordinator(coordinator);
        log.info("Registered CoordinatorId: "+ coordinatorId);
        return coordinatorId;
    }
}
