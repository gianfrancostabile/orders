package com.ar.gfstabile.orders.dto.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ar.gfstabile.orders.constant.OrderState;
import com.ar.gfstabile.orders.dto.common.PersonDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public record OrderResponseDto(@JsonProperty("id") String id, @JsonProperty("client") PersonDto client,
        @JsonProperty("products") List<ProductResponseDto> products,
        @JsonProperty("total_price") Double totalPrice,
        @JsonProperty("state") OrderState state,
        @JsonProperty("creation_date") @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mmZZZ") Date creationDate) implements Serializable {

}
