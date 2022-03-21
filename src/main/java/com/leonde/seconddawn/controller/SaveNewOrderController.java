package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.DockOrderRequest;
import com.leonde.seconddawn.service.DefaultShipOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@Slf4j
public class SaveNewOrderController implements SaveOrderController {

    @Autowired
    DefaultShipOrderService defaultShipOrderService;


    @Override
    public DockOrder createOrder(DockOrderRequest orderRequest) {
        log.info("request ={}", orderRequest);
        return defaultShipOrderService.createOrder(orderRequest);
    }
}
