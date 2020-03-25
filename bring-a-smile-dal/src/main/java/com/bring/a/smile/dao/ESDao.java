package com.bring.a.smile.dao;

import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Optional;


@Slf4j
public class ESDao implements IESDao {

    private Client esClient;

    public ESDao(Client esClient) {
        this.esClient = esClient;
    }

    //TODO : Debug and find out if it throws an exception on its own
    @Override
    public String createDocument(String namespace, String id, String documentJson) throws DuplicateEntryException {
        GetResponse getResponse = esClient.prepareGet(namespace,namespace,id).get();
        if (getResponse.isExists()){
            throw new DuplicateEntryException("Id: "+ id + " is already present");
        }
        IndexResponse indexResponse =  esClient.prepareIndex(namespace,namespace).setId(id).setSource(documentJson, XContentType.JSON).get();
        return indexResponse.getId();
    }

    @Override
    public Optional<String> getDocument(String namespace, String id) {
        GetResponse getResponse = esClient.prepareGet(namespace,namespace,id).get();
        if (!getResponse.isExists() || getResponse.isSourceEmpty()){
            return Optional.empty();
        }
        return Optional.of(getResponse.getSourceAsBytes().toString());
    }

    @Override
    public void update(String namespace, String id, String documentJson) throws NotFoundException {
        GetResponse getResponse = esClient.prepareGet(namespace,namespace,id).get();
        if (!getResponse.isExists()){
            throw new NotFoundException("Id: "+ id + " is not present");
        }
        long prevVersionNumber = getResponse.getVersion();
        UpdateResponse updateResponse = esClient.prepareUpdate(namespace,namespace,id).setDoc(documentJson, XContentType.JSON).get();
        long newVersion = updateResponse.getVersion();
        if (newVersion==prevVersionNumber){
            log.error("UpdateFailure. new version number : "+ newVersion + " is same as old version: "+ prevVersionNumber);
            throw new RuntimeException("UpdateFailure. new version number : "+ newVersion + " is same as old version: "+ prevVersionNumber);
        }
        log.info("Id: "+ id + " updated from version : "+ prevVersionNumber + " to version: "+ newVersion);
    }
}