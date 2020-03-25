package com.bring.a.smile.dao;


import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.exception.NotFoundException;
import com.bring.a.smile.model.*;
import com.bring.a.smile.utils.DalUtils;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

//TODO : Create an ES DataTypes Enum
//TODO: Read namespace names from config

@Slf4j
public class AssociationDao {

    private static String namespace = "association";

    private IESDao esDao;
    private IESSearchDao esSearchDao;
    private ESUtils esUtils;
    private DalUtils dalUtils;


    public AssociationDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils, DalUtils dalUtils) {
        this.esDao = esDao;
        this.esSearchDao = esSearchDao;
        this.esUtils = esUtils;
        this.dalUtils = dalUtils;
    }

    public void markComplete(String volunteerId, String requestId) throws NotFoundException {
        String id = dalUtils.getAssociationId(volunteerId, requestId);
        Association association = new Association(null, null, null, true);
        String associationDocumentString;
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
        String associationDocumentString;
        try {
            associationDocumentString = esUtils.serialize(association);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + association,e);
            throw new RuntimeException("Could not serialise Object " + association, e);
        }
        String associationId = esDao.createDocument(namespace, id, associationDocumentString);
        return associationId;
    }


    public Long getAssociationCount(String goodwillRequestId) {
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
        SearchResponseList searchResponseList = esSearchDao.search(namespace,searchTerms, null,
                0,0);
        return searchResponseList.getCount();
    }
}
