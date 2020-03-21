package com.bring.a.smile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodWillRequestCompleteBody {
    private String requestId;
    List<String> userIDs;
}
