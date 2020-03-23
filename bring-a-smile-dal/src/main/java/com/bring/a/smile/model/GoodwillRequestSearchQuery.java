package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GoodwillRequestSearchQuery {
    private String coordinatorId;
    private String volunteerId;
    private LocalDateTime dateGreater;
    private Boolean isVolunteersRequired;
    private String pincode;
    private int start;
    private int limit;
}
