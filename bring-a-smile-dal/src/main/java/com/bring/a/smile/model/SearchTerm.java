package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//TODO: List<Object> should be List<String> with a deserialiser. Use a deserialiser instead of typecasting the object

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SearchTerm {
    private Field field;
    private QueryType queryType;
    List<Object> comparisonFields;
}
