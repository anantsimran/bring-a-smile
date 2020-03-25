package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodWillRequestExtended {
    private GoodwillRequest goodwillRequest;
    private Long totalVolunteers;
}
