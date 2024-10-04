package com.ar.gfstabile.orders.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ResponseHandler {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T map(String value, Class<T> clazz) {
        try {
            return objectMapper.readValue(value, clazz);
        } catch (Exception e) {
            return null;
        }
    }
}
