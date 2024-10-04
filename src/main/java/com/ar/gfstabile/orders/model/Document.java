package com.ar.gfstabile.orders.model;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ar.gfstabile.orders.constant.DocumentType;

public record Document(@Field("type") DocumentType type, @Field("number") String number) {

}
