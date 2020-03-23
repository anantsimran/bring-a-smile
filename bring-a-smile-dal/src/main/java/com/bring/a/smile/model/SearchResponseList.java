package com.bring.a.smile.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SearchResponseList {
    private Integer count;
    private Integer start;
    private Integer limit;
    private List<String> documents;
}
