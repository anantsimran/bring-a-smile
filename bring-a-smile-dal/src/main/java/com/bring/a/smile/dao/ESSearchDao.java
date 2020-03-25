package com.bring.a.smile.dao;

import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.model.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;


@Slf4j
public class ESSearchDao implements IESSearchDao {

    private Client esClient;

    public ESSearchDao(Client esClient) {
        this.esClient = esClient;
    }

    @Override
    public SearchResponseList search(String namespace, List<SearchTerm> searchTerms, SortTerm sortTerm,
                                     int start, int limit) {

        SearchRequestBuilder searchRequestBuilder =  esClient.prepareSearch(namespace);
        if (CollectionUtils.isNotEmpty(searchTerms)){
            BoolQueryBuilder boolQueryBuilder = getBoolQueryBuilder(searchTerms);
            searchRequestBuilder.setQuery(boolQueryBuilder);
        }
        if (sortTerm!=null){
            searchRequestBuilder.addSort(sortTerm.getField().getFieldName(),getSortOrder(sortTerm.getSortOrder()));
        }
        SearchResponse searchResponse =  searchRequestBuilder.setFrom(start).setSize(limit).get();


        List<String> documents = Lists.newArrayList();
        for(SearchHit searchHit: searchResponse.getHits().getHits()){
            documents.add(searchHit.getSourceAsString());
        }

        SearchResponseList searchResponseList = SearchResponseList.builder()
                .start(start)
                .limit(limit)
                .count(searchResponse.getHits().totalHits)
                .documents(documents)
                .build();
        return searchResponseList;
    }

    SortOrder getSortOrder(com.bring.a.smile.model.SortOrder sortOrder){
        switch (sortOrder){
            case ASC: return SortOrder.ASC;
            case DESC:return SortOrder.DESC;
        }
        throw new IllegalArgumentException("SortOrder: + "+ sortOrder+ " not recognized");
    }

    private BoolQueryBuilder getBoolQueryBuilder(List<SearchTerm> searchTerms) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for(SearchTerm searchTerm: searchTerms){
            QueryBuilder queryBuilder= null;
            if (searchTerm.getField().getFieldType().equals(FieldType.STRING)) {
                queryBuilder = getQueryBuilderForString(searchTerm);

            }
            if (searchTerm.getField().getFieldType().equals(FieldType.LONG)) {
                queryBuilder = getQueryBuilderForLong(searchTerm);

            }
            if (searchTerm.getField().getFieldType().equals(FieldType.DATE)) {
                queryBuilder = getQueryBuilderForDate(searchTerm);

            }
            boolQueryBuilder.must(queryBuilder);
        }
        return boolQueryBuilder;
    }

    private QueryBuilder getQueryBuilderForLong(SearchTerm searchTerm) {
        QueryBuilder queryBuilder;
        if (searchTerm.getQueryType().equals(QueryType.EQUALS)) {
            queryBuilder = QueryBuilders.termQuery(searchTerm.getField().getFieldName(),
                    Long.valueOf(searchTerm.getComparisonFields().get(0).toString()));
        }
        else if (searchTerm.getQueryType().equals(QueryType.BETWEEN)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .gte(Long.valueOf(searchTerm.getComparisonFields().get(0).toString()))
                    .lt(Long.valueOf(searchTerm.getComparisonFields().get(1).toString()));
        }
        else if (searchTerm.getQueryType().equals(QueryType.GREATER_OR_EQUAL)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .gte(Long.valueOf(searchTerm.getComparisonFields().get(0).toString()));
        }
        else if (searchTerm.getQueryType().equals(QueryType.LESSER)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .lt(Long.valueOf(searchTerm.getComparisonFields().get(0).toString()));
        }
        else{
            log.error("Query Type: "+ searchTerm.getQueryType()+ " not supported for Long");
            throw new IllegalArgumentException(" Query Type: "+ searchTerm.getQueryType()+ " not supported for Long");
        }
        return queryBuilder;
    }

    private QueryBuilder getQueryBuilderForDate(SearchTerm searchTerm) {
        QueryBuilder queryBuilder;
        if (searchTerm.getQueryType().equals(QueryType.EQUALS)) {
            queryBuilder = QueryBuilders.termQuery(searchTerm.getField().getFieldName(),
                    searchTerm.getComparisonFields().get(0).toString());
        }
        else if (searchTerm.getQueryType().equals(QueryType.BETWEEN)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .gte(searchTerm.getComparisonFields().get(0).toString())
                    .lt(searchTerm.getComparisonFields().get(1).toString());
        }
        else if (searchTerm.getQueryType().equals(QueryType.GREATER_OR_EQUAL)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .gte(searchTerm.getComparisonFields().get(0).toString());
        }
        else if (searchTerm.getQueryType().equals(QueryType.LESSER)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .lt(searchTerm.getComparisonFields().get(0).toString());
        }
        else{
            log.error("Query Type: "+ searchTerm.getQueryType()+ " not supported for Long");
            throw new IllegalArgumentException("Query Type: "+ searchTerm.getQueryType()+ " not supported for Long");
        }
        return queryBuilder;
    }




    private QueryBuilder getQueryBuilderForString(SearchTerm searchTerm) {
        QueryBuilder queryBuilder;
        if (searchTerm.getQueryType().equals(QueryType.EQUALS)) {
            queryBuilder = QueryBuilders.termQuery(searchTerm.getField().getFieldName(),
                    (searchTerm.getComparisonFields().get(0)).toString());
        }
        else if (searchTerm.getQueryType().equals(QueryType.BETWEEN)) {
            queryBuilder = QueryBuilders.rangeQuery(searchTerm.getField().getFieldName())
                    .gte(searchTerm.getComparisonFields().get(0).toString())
                    .lt(searchTerm.getComparisonFields().get(1).toString());
        }
        else {
            log.error("Query Type: "+ searchTerm.getQueryType()+ " not supported for String");
            throw new IllegalArgumentException(" Query Type: "+ searchTerm.getQueryType()+ " not supported for String");
        }
        return queryBuilder;
    }
}
