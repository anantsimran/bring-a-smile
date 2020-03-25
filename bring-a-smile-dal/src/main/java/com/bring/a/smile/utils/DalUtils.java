package com.bring.a.smile.utils;

import com.bring.a.smile.model.Volunteer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

@Slf4j
public class DalUtils {
    public String getAssociationId(String volunteerId, String goodwillRequestId) {
        return DigestUtils.md5(volunteerId+ "_"+ goodwillRequestId).toString();
    }

    public String getVolunteerId(Volunteer volunteer) {
        return DigestUtils.md5(volunteer.getContactNo()).toString();
    }

}
