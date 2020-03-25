package com.bring.a.smile.dao;


import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.*;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
public class GoodwillRequestDao {

    private static String namespace = "request";

    private IESDao esDao;
    private IESSearchDao esSearchDao;
    private ESUtils esUtils;

    public GoodwillRequestDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils) {
        this.esDao = esDao;
        this.esSearchDao = esSearchDao;
        this.esUtils = esUtils;
    }

    public String createGoodwillRequest(GoodwillRequest goodwillRequest) throws DuplicateEntryException {
        String id = UUID.randomUUID().toString();
        goodwillRequest.setRequestId(id);
        String goodwillRequestDocString;
        try {
            goodwillRequestDocString = esUtils.serialize(goodwillRequest);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + goodwillRequest, e);
            throw new RuntimeException("Could not serialise Object " + goodwillRequest, e);
        }
        esDao.createDocument(namespace, id, goodwillRequestDocString);
        return id;
    }


    public List<GoodwillRequest> search(GoodwillRequestSearchQuery searchQuery) {
        List<SearchTerm> searchTerms =
                Arrays.asList(
                        SearchTerm.builder()
                                .field(new Field("coordinatorId", FieldType.STRING))
                                .queryType(QueryType.EQUALS)
                                .comparisonFields(
                                        Arrays.asList(searchQuery.getCoordinatorId())
                                )
                                .build()
                );
        SearchResponseList searchResponseList = esSearchDao.search(namespace, searchTerms, null,
                searchQuery.getStart(), searchQuery.getLimit());
        List<GoodwillRequest> list = new ArrayList<>();
        for (String s : searchResponseList.getDocuments()) {
            GoodwillRequest deserialize = null;
            try {
                deserialize = esUtils.deserialize(s, GoodwillRequest.class);
            } catch (IOException e) {
                log.error("Could not deserialise Object " + s, e);
                throw new RuntimeException("Could not deserialise Object " + s, e);
            }
            list.add(deserialize);
        }
        return list;
    }
}
