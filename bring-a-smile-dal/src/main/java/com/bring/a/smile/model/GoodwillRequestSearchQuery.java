package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GoodwillRequestSearchQuery {
    private String coordinatorId;
    private Date dateGreater;
    private String pincode;
    private boolean urgent;
    private int start;
    private int limit;
}
