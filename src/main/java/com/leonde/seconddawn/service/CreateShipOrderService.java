package com.leonde.seconddawn.service;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.DockOrderRequest;

public interface CreateShipOrderService {
    DockOrder createOrder(DockOrderRequest orderRequest);
}
