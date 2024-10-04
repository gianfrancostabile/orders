package com.ar.gfstabile.orders.dto.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PersonDto(@JsonProperty("name") String name, @JsonProperty("surname") String surname,
        @JsonProperty("document") DocumentDto document) implements Serializable {
}
