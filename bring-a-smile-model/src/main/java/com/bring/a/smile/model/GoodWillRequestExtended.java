package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodWillRequestExtended {
    private GoodwillRequest goodwillRequest;
    private Long totalVolunteers;
}
