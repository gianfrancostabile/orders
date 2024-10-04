package com.ar.gfstabile.orders.service.impl;

import org.springframework.stereotype.Service;

import com.ar.gfstabile.orders.constant.CanceledOrderState;
import com.ar.gfstabile.orders.service.OrderStateService;

@Service
public class CancelOrderStateService extends OrderStateService<CanceledOrderState> {

    @Override
    protected CanceledOrderState getNewState() {
        return new CanceledOrderState();
    }

}
