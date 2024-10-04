package com.ar.gfstabile.orders.constant;

public sealed interface OrderState permits PendingOrderState, ProcessedOrderState, CanceledOrderState {
    State getState();
}