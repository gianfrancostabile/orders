package com.ar.gfstabile.orders.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ar.gfstabile.orders.constant.Constant;
import com.ar.gfstabile.orders.constant.OrderState;
import com.ar.gfstabile.orders.dto.request.OrderRequestDto;
import com.ar.gfstabile.orders.dto.request.ProductRequestDto;
import com.ar.gfstabile.orders.dto.response.OrderResponseDto;
import com.ar.gfstabile.orders.model.Order;
import com.ar.gfstabile.orders.model.Product;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "state", source = "orderState")
    @Mapping(target = "totalPrice", expression = "java(com.ar.gfstabile.orders.constant.Constant.MINIMUN_PRICE)")
    @Mapping(target = "creationDate", expression = "java(new java.util.Date())")
    Order mapRequestToModel(OrderRequestDto request, OrderState orderState);

    OrderResponseDto mapModelToResponse(Order order);

    default String mapObjectString(ObjectId id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return id.toHexString();
    }

    default ObjectId mapToObjectString(String id) {
        if (!StringUtils.hasText(id)) {
            return null;
        }
        return new ObjectId(id);
    }

    default List<Product> mapProducts(List<ProductRequestDto> products) {
        if (CollectionUtils.isEmpty(products)) {
            return new ArrayList<>();
        }
        return products.stream().map(product -> {
            Integer quantity = (product.quantity() <= Constant.MINIMUN_QUANTITY) ? Constant.MINIMUN_QUANTITY
                    : product.quantity();
            Double unitPrice = Constant.DEFAULT_PRICE;
            Double totalPrice = unitPrice * quantity;
            return new Product(product.code(), quantity, unitPrice, totalPrice);
        }).collect(Collectors.toList());
    }
}
