package com.ar.gfstabile.orders.model;

import org.springframework.data.mongodb.core.mapping.Field;

public record Person(@Field("name") String name, @Field("surname") String surname,
        @Field("document") Document document) {
                
}
