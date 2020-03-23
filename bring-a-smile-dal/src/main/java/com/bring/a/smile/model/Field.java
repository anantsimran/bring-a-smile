package com.bring.a.smile.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Field {
    private String fieldName;
    private FieldType fieldType;
}
