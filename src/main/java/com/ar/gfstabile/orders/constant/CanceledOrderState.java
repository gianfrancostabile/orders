package com.ar.gfstabile.orders.constant;

public final class CanceledOrderState implements OrderState {

    @Override
    public State getState() {
        return State.CANCELED;
    }

}
