package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class GoodwillRequestSearchQuery {
    private String coordinatorId;
    private String volunteerId;
    private LocalDate dateGreater;
    private LocalTime timeGreater;
    private boolean isOpen;
    private String pincode;
}
