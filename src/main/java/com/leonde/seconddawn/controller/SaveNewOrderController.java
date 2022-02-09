package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.DockOrderRequest;
import com.leonde.seconddawn.service.DefaultShipOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class SaveNewOrderController implements SaveOrderController {

    @Autowired
    DefaultShipOrderService defaultShipOrderService;

    @GetMapping("/json")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Map<String, Integer> getJson()  {
        return Map.of("number", 3);
    }

    @Override
    public DockOrder createOrder(DockOrderRequest orderRequest) {
        return defaultShipOrderService.createOrder(orderRequest);
    }
}
