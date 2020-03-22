package com.bring.a.smile.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Association {
    private String id;
    private String goodwillRequestId;
    private String volunteerId;
    boolean isComplete;
}
