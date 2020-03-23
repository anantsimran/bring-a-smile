package com.bring.a.smile.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class ESUtils {

    private ObjectMapper objectMapper;

    public ESUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T deserialize(String documentString, Class<T> documentClass) throws IOException {
        return objectMapper.readValue(documentString, documentClass);
    }

    public <T >String serialize(T document) throws JsonProcessingException {
        return objectMapper.writeValueAsString(document);
    }
}
