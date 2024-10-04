package com.ar.gfstabile.orders.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDataResponseDto(
        @JsonProperty("code") String code,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("price") Double price) implements Serializable {

}
