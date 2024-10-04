package com.ar.gfstabile.orders.dto.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceDto(
        @JsonProperty("code") String code,
        @JsonProperty("price") Double price) implements Serializable {
}
