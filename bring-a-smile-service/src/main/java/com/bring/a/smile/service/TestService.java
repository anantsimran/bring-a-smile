package com.bring.a.smile.service;


import com.bring.a.smile.model.MessageResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestService {

    public MessageResponse getTestString(){
        return new MessageResponse("Okay Boomer");
    }

}
