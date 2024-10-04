package com.ar.gfstabile.orders.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ar.gfstabile.orders.constant.State;
import com.ar.gfstabile.orders.service.OrderStateService;
import com.ar.gfstabile.orders.service.impl.CancelOrderStateService;
import com.ar.gfstabile.orders.service.impl.ProcessOrderStateService;

@Component
public class OrderStateBuilder {

    @Autowired
    private CancelOrderStateService cancelOrderStateService;

    @Autowired
    private ProcessOrderStateService processOrderStateService;

    public OrderStateService<?> build(State orderState) {
        return switch (orderState) {
            case PROCESSED -> processOrderStateService;
            case CANCELED -> cancelOrderStateService;
            default -> throw new Error("status not found");
        };
    }
}
