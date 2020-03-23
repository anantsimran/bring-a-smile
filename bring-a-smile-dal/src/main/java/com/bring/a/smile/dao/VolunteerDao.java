package com.bring.a.smile.dao;


import com.bring.a.smile.api.IESDao;
import com.bring.a.smile.api.IESSearchDao;
import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.model.Volunteer;
import com.bring.a.smile.utils.ESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public class VolunteerDao {

    private static String namespace = "volunteer";


    private IESDao esDao;
    private IESSearchDao esSearchDao;
    private ESUtils esUtils;

    public String register(Volunteer volunteer) throws DuplicateEntryException {
        String id = DigestUtils.md5(volunteer.getContactNo()).toString();
        volunteer.setId(id);
        String volunteerDocumentString = null;
        try {
            volunteerDocumentString = esUtils.serialize(volunteer);
        } catch (JsonProcessingException e) {
            log.error("Could not serialise Object " + volunteer);
            throw new RuntimeException("Could not serialise Object " + volunteer, e);
        }
        esDao.createDocument(namespace, id, volunteerDocumentString);
        return id;
    }
}
