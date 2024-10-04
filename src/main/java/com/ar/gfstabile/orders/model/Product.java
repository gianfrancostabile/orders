package com.ar.gfstabile.orders.model;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Field("_id")
    private String code;

    @Field("quantity")
    private Integer quantity;

    @Field("unit-price")
    private Double unitPrice;

    @Field("total-price")
    private Double totalPrice;

    public void setUnitPrice(Double unitPrice) {
        if (Objects.isNull(unitPrice) || unitPrice < 0L) {
            this.unitPrice = 0D;
        } else {
            this.unitPrice = unitPrice;
        }
        this.setTotalPrice(unitPrice * this.getQuantity());
    }

    public Double getUnitPrice() {
        if (Objects.isNull(unitPrice) || unitPrice <= 0L) {
            this.unitPrice = 0D;
        }
        return this.unitPrice;
    }

    public void setQuantity(Integer quantity) {
        if (Objects.isNull(quantity) || quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
        this.setTotalPrice(this.getUnitPrice() * quantity);
    }

    public Integer getQuantity() {
        if (Objects.isNull(quantity) || quantity < 0) {
            this.quantity = 0;
        }
        return this.quantity;
    }
}
