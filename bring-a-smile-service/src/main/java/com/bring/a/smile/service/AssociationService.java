package com.bring.a.smile.service;

import com.bring.a.smile.dao.AssociationDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.exception.NotFoundException;
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

    public void completeGoodwillRequest(GoodWillRequestCompleteBody goodwillRequestCompleteBody) throws NotFoundException {
        for(String volunteerId: goodwillRequestCompleteBody.getVolunteerIds()){
            associationDao.markComplete(volunteerId, goodwillRequestCompleteBody.getRequestId());
        }
    }

    public void enroll(String volunteerId, String goodwillRequestId) throws DuplicateEntryException {
        String associationId =  associationDao.createAssociation(volunteerId, goodwillRequestId);
        log.info("Created Association:+"+ associationId);
    }
}
