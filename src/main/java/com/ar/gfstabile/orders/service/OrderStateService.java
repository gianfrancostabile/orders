package com.ar.gfstabile.orders.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ar.gfstabile.orders.constant.OrderState;
import com.ar.gfstabile.orders.constant.PendingOrderState;
import com.ar.gfstabile.orders.model.Order;
import com.ar.gfstabile.orders.repository.OrderRespository;

public abstract class OrderStateService<T extends OrderState> {

    @Autowired
    private OrderRespository repository;

    public void changeState(String id) {
        if (!StringUtils.hasText(id)) {
            throw new Error("order id is required");
        }
        Optional<Order> orderOptional = repository.findById(new ObjectId(id));
        if (!orderOptional.isPresent()) {
            throw new Error("order not found");
        }
        Order order = orderOptional.get();
        if (!(order.getState() instanceof PendingOrderState)) {
            throw new Error("order is not pending");
        }
        order.setState(this.getNewState());
        repository.save(order);
    }

    protected abstract T getNewState();
}
