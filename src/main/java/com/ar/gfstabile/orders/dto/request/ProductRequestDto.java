package com.ar.gfstabile.orders.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRequestDto(@JsonProperty("code") String code, @JsonProperty("quantity") Integer quantity)
        implements Serializable {

}
