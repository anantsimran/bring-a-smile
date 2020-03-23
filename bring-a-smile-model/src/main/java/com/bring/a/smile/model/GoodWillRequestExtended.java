package com.bring.a.smile.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class GoodWillRequestExtended extends GoodwillRequest {
    private Integer totalVolunteers;

    public GoodWillRequestExtended(String requestID, String coordinatorId, LocalDateTime startTime, LocalDateTime endTime,
                                   Address address, Priority priority, Integer minimumCoordinatorsRequired,
                                   boolean isRequiringVolunteers, Integer totalVolunteers) {
        super(requestID, coordinatorId, startTime, endTime, address, priority, minimumCoordinatorsRequired, isRequiringVolunteers);
        this.totalVolunteers = totalVolunteers;
    }
    public GoodWillRequestExtended(GoodwillRequest goodwillRequest, Integer totalVolunteers) {
        super(goodwillRequest.getRequestId(), goodwillRequest.getCoordinatorId(),
                goodwillRequest.getStartTime(), goodwillRequest.getStartTime(),
                goodwillRequest.getAddress(), goodwillRequest.getPriority(),
                goodwillRequest.getMinimumCoordinatorsRequired(), goodwillRequest.isRequiringVolunteers());
        this.totalVolunteers = totalVolunteers;
    }

}
