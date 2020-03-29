package com.bring.a.smile.dao;

import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.Coordinator;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public class CoordinatorDao {

    private static String namespace = "coordinator";


    private IESDao esDao;
    private IESSearchDao esSearchDao;
    private ESUtils esUtils;

    public CoordinatorDao(IESDao esDao, IESSearchDao esSearchDao, ESUtils esUtils) {
        this.esDao = esDao;
        this.esSearchDao = esSearchDao;
        this.esUtils = esUtils;
    }

    public String registerCoordinator(Coordinator coordinator) throws DuplicateEntryException {
        String id = DigestUtils.md5Hex(coordinator.getContactNo());
        coordinator.setId(id);
        String coordinatorDocumentString = null;
        try {
            coordinatorDocumentString = esUtils.serialize(coordinator);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + coordinator, e);
            throw new RuntimeException("Could not serialise Object " + coordinator, e);
        }
        esDao.createDocument(namespace, id, coordinatorDocumentString);
        return id;
    }
}
