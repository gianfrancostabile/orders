package com.ar.gfstabile.orders.constant;

public final class PendingOrderState implements OrderState {

    @Override
    public State getState() {
        return State.PENDING;
    }

}