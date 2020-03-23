package com.bring.a.smile.api;

import com.bring.a.smile.model.SearchResponseList;
import com.bring.a.smile.model.SearchTerm;
import com.bring.a.smile.model.SortTerm;

import java.util.List;

public interface IESSearchDao {
    //TODO : put iterator


    SearchResponseList search(String namespace, List<SearchTerm> searchTerms, List<SortTerm> sortTerms,
                              int start, int limit);

}
