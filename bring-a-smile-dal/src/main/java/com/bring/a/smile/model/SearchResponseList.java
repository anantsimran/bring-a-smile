package com.bring.a.smile.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SearchResponseList {
    private Long count;
    private Integer start;
    private Integer limit;
    private List<String> documents;
}
