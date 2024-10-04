package com.ar.gfstabile.orders.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponseDto(@JsonProperty("code") String code, @JsonProperty("quantity") Integer quantity,
        @JsonProperty("unit_price") Double unitPrice, @JsonProperty("total_price") Double totalPrice)
        implements Serializable {

}
