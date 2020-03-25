package com.bring.a.smile.api;

import com.bring.a.smile.model.SearchResponseList;
import com.bring.a.smile.model.SearchTerm;
import com.bring.a.smile.model.SortTerm;

import java.util.List;

//TODO: Build a ES iterator in case this scales

public interface IESSearchDao {

    SearchResponseList search(String namespace, List<SearchTerm> searchTerms, SortTerm sortTerm,
                              int start, int limit);

}
