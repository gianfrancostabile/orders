package com.ar.gfstabile.orders.constant;

public final class ProcessedOrderState implements OrderState {

    @Override
    public State getState() {
        return State.PROCESSED;
    }

}
