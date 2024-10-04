package com.ar.gfstabile.orders.dto.request;

import java.io.Serializable;
import java.util.List;

import com.ar.gfstabile.orders.constant.OrderState;
import com.ar.gfstabile.orders.dto.common.DocumentDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequestDto(@JsonProperty("id") String id, @JsonProperty("client_document") DocumentDto clientDocument,
        @JsonProperty("products") List<ProductRequestDto> products, @JsonProperty("state") OrderState state)
        implements Serializable {

}
