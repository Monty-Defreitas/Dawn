package com.leonde.seconddawn.controller;


import com.leonde.seconddawn.dao.UpdateOrderDao;
import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.UpdateDockOrder;
import com.leonde.seconddawn.entity.Weapons;
import com.leonde.seconddawn.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultUpdateTable implements UpdateOrder{

    @Autowired
    UpdateService updateService;

    @Autowired
    UpdateOrderDao updateOrderDao;



//    @Override
//    public DockOrder updateOrder(UpdateDockOrder someKey) {
//        return updateOrderDao.updateAndReturnUpdate(someKey);
//    }


//    @Override
//    public DockOrder updateOrder(String someKey, String rowName) {
//        return null;
//    }

    @Override
    public DockOrder updateOrder(UpdateDockOrder updateDockOrder) {
        return updateService.updateOrderService(updateDockOrder);
    }

    @Override
    public Weapons updateWeapon(String weapon, String orderId) {
        return updateService.updateWeaponService(orderId, weapon);
    }

    @Override
    public String deleteOrder(String orderId) {
        return updateService.deleteService(orderId);
    }
}
