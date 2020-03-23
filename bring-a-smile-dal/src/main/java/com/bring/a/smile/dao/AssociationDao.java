package com.bring.a.smile.dao;


import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.exception.NotFoundException;
import com.bring.a.smile.model.*;
import com.bring.a.smile.utils.DalUtils;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

//TODO : Take care of injection and initialisation
//TODO : Create an ES DataTypes Enum

@Slf4j
public class AssociationDao {
    //TODO: Read from config
    private static String namespace = "association";

    private IESDao esDao;
    private IESSearchDao esSearchDao;
    private ESUtils esUtils;
    private DalUtils dalUtils;


    public void markComplete(String volunteerId, String requestId) throws NotFoundException {
        String id = dalUtils.getAssociationId(volunteerId, requestId);
        Association association = new Association(null, null, null, true);
        String associationDocumentString = null;
        try {
            associationDocumentString = esUtils.serialize(association);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + association,e);
            throw new RuntimeException("Could not serialise Object " + association, e);
        }
        esDao.update(namespace, id, associationDocumentString);
    }


    public String createAssociation(String volunteerId, String goodwillRequestId) throws DuplicateEntryException {
        String id = dalUtils.getAssociationId(volunteerId, goodwillRequestId);
        Association association = new Association(id, goodwillRequestId, volunteerId, false);
        String associationDocumentString = null;
        try {
            associationDocumentString = esUtils.serialize(association);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + association,e);
            throw new RuntimeException("Could not serialise Object " + association, e);
        }
        String associationId = esDao.createDocument(namespace, id, associationDocumentString);
        return associationId;
    }


    public Integer getAssociationCount(String goodwillRequestId) {
        List<SearchTerm> searchTerms =
                Arrays.asList(
                        SearchTerm.builder()
                                .field(new Field("goodwillRequestId", FieldType.STRING))
                                .queryType(QueryType.EQUALS)
                                .comparisonFields(
                                        Arrays.asList(goodwillRequestId)
                                )
                                .build()
                );
        SearchResponseList searchResponseList = esSearchDao.search(namespace,searchTerms, Lists.newArrayList(), 0,1);
        return searchResponseList.getCount();
    }
}
