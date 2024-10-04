package com.ar.gfstabile.orders.dto.common;

import java.io.Serializable;

import com.ar.gfstabile.orders.constant.DocumentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentDto(@JsonProperty("type") DocumentType type, @JsonProperty("number") String number)
        implements Serializable {

}
