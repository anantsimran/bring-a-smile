package com.bring.a.smile.service;

import com.bring.a.smile.dao.AssociationDao;
import com.bring.a.smile.dao.GoodwillRequestDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.GoodWillRequestExtended;
import com.bring.a.smile.model.GoodwillRequest;
import com.bring.a.smile.model.GoodwillRequestSearchQuery;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GoodWillRequestService {

    private GoodwillRequestDao goodwillRequestDao;
    private AssociationDao associationDao;

    @Inject
    public GoodWillRequestService(GoodwillRequestDao goodwillRequestDao, AssociationDao associationDao) {
        this.goodwillRequestDao = goodwillRequestDao;
        this.associationDao = associationDao;
    }

    public String createGoodwillRequest(GoodwillRequest goodwillRequest) throws DuplicateEntryException {
        String goodWillRequestId =  goodwillRequestDao.createGoodwillRequest(goodwillRequest);
        log.info("Created goodwillRequestId: "+ goodWillRequestId);
        return goodWillRequestId;
    }

    public List<GoodWillRequestExtended> search(GoodwillRequestSearchQuery searchQuery) {
        List<GoodwillRequest> goodwillRequests=  goodwillRequestDao.search(searchQuery);
        List<GoodWillRequestExtended> goodWillRequestExtendedList = Lists.newArrayList();
        for(GoodwillRequest goodwillRequest : goodwillRequests){
            Long associationCount = associationDao.getAssociationCount(goodwillRequest.getRequestId());
            goodWillRequestExtendedList.add(new GoodWillRequestExtended(goodwillRequest, associationCount));
        }
        if (searchQuery.isUrgent()){
            return goodWillRequestExtendedList.stream().filter(g-> g.getGoodwillRequest().getMinimumVolunteersRequired()> g.getTotalVolunteers())
                    .collect(Collectors.toList());
        }

        return goodWillRequestExtendedList;
    }
}
