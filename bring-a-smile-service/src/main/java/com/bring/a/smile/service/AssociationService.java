package com.bring.a.smile.service;

import com.bring.a.smile.dao.AssociationDao;
import com.bring.a.smile.model.GoodWillRequestCompleteBody;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AssociationService {
    private AssociationDao associationDao;

    @Inject
    public AssociationService(AssociationDao associationDao) {
        this.associationDao = associationDao;
    }

    public void completeGoodwillRequest(GoodWillRequestCompleteBody goodwillRequestCompleteBody) {
        for(String volunteerId: goodwillRequestCompleteBody.getVolunteerIds()){
            associationDao.markComplete(goodwillRequestCompleteBody.getRequestId(), volunteerId);
        }
    }

    public void enroll(String volunteerId, String goodwillRequestId) {
        String associationId =  associationDao.createAssociation(volunteerId, goodwillRequestId);
        log.info("Created Association:+"+ associationId);
    }
}
