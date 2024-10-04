package com.ar.gfstabile.orders.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ar.gfstabile.orders.constant.OrderState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Field("_id")
    private ObjectId id;

    @Field("client")
    private Person client;

    @Field("products")
    private List<Product> products;

    @Field("total-price")
    private Double totalPrice;

    @Field("state")
    private OrderState state;

    @Field("creation-date")
    private Date creationDate;
}
