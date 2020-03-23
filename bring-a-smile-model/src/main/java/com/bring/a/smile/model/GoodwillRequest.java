package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GoodwillRequest {
    private String requestId;
    private String coordinatorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Address address;
    private Priority priority;
    private Integer minimumCoordinatorsRequired;
    private boolean isRequiringVolunteers;
}
