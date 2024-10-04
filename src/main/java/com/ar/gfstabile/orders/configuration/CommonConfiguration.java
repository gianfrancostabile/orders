package com.ar.gfstabile.orders.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CommonConfiguration {
    
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
