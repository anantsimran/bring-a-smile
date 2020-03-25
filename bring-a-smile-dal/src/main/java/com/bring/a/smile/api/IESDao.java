package com.bring.a.smile.api;

import com.bring.a.smile.exception.DuplicateEntryException;
import com.bring.a.smile.exception.NotFoundException;

import java.util.Optional;



public interface IESDao {


    String createDocument(String namespace, String id, String documentJson) throws DuplicateEntryException;

    Optional<String> getDocument(String namespace, String id);

    void update(String namespace, String id, String documentJson) throws NotFoundException;
}
