package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GoodwillRequest {
    private String requestId;
    private String coordinatorId;
    private Date startTime;
    private Date endTime;
    private String description;
    private Address address;
    private Priority priority;
    private Integer minimumVolunteersRequired;
    private boolean isOpen;
}
