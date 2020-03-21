package com.bring.a.smile.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GoodWillRequestExtended extends GoodwillRequest {
    private Integer totalVolunteers;

    public GoodWillRequestExtended(String requestID, String coordinatorId, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   Address address, Priority priority, Integer minimumCoordinatorsRequired,
                                   boolean isOpen, Integer totalVolunteers) {
        super(requestID, coordinatorId, date, startTime, endTime, address, priority, minimumCoordinatorsRequired, isOpen);
        this.totalVolunteers = totalVolunteers;
    }
}
