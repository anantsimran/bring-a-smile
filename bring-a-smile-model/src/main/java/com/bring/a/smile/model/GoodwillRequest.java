package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
public class GoodwillRequest {
    private String requestID;
    private String coordinatorId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Address address;
    private Priority priority;
    private Integer minimumCoordinatorsRequired;
    private boolean isOpen;
}
