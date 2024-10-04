package com.ar.gfstabile.orders.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentType {
    DU, CUIL, CUIT;

    @JsonValue
    public String getValue() {
        return this.name().toUpperCase();
    }
}
