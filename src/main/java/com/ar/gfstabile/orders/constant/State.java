package com.ar.gfstabile.orders.constant;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum State {
    PENDING("Pendiente"), PROCESSED("Procesada"), CANCELED("Cancelada");

    private String capitalize;
    private String translation;

    private State(String translation) {
        this.capitalize = StringUtils.capitalize(this.name());
        this.translation = translation;
    }

    public String getCapitalize() {
        return this.capitalize;
    }

    public String getTranslation() {
        return this.translation;
    }

    @JsonValue
    public String getValue() {
        return this.name().toUpperCase();
    }
}
