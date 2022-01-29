package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.DockOrderRequest;
import com.leonde.seconddawn.service.DefaultShipOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveNewOrderController implements SaveOrderController {

    @Autowired
    DefaultShipOrderService defaultShipOrderService;

    @Override
    public DockOrder createOrder(DockOrderRequest orderRequest) {
        return defaultShipOrderService.createOrder(orderRequest);
    }
}
