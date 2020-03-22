package com.bring.a.smile.service;

import com.bring.a.smile.dao.AuthorizationDao;
import com.bring.a.smile.dao.CoordinatorDao;
import com.bring.a.smile.model.Coordinator;
import com.bring.a.smile.model.MessageResponse;
import com.bring.a.smile.model.UserAuthorization;
import com.bring.a.smile.model.UserType;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoordinatorService {

    private CoordinatorDao coordinatorDao;
    private AuthorizationDao authorizationDao;

    @Inject
    public CoordinatorService(CoordinatorDao coordinatorDao, AuthorizationDao authorizationDao) {
        this.coordinatorDao = coordinatorDao;
        this.authorizationDao = authorizationDao;
    }

    public MessageResponse getLoginMessage(com.bring.a.smile.auth.User user) {
        return new MessageResponse(user + " is logged in");
    }

    public String registerCoordinator(Coordinator coordinator) {
        String coordinatorId=  coordinatorDao.registerCoordinator(coordinator);
        authorizationDao.register(new UserAuthorization(coordinatorId, UserType.COORDINATOR, coordinator.getPasswordHash()));
        log.info("Registered CoordinatorId: "+ coordinatorId);
        return coordinatorId;
    }
}
