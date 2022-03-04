package com.leonde.seconddawn.service;


import com.leonde.seconddawn.dao.UpdateOrderDao;
import com.leonde.seconddawn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UpdateService {

    @Autowired
    UpdateOrderDao updateOrderDao;

    public DockOrder updateOrderService(UpdateDockOrder updateDockOrder) {

        Weapons weapons = getWeapons(updateDockOrder);
        Shields shields = getShields(updateDockOrder);
        Hulls hulls = getHulls(updateDockOrder);
//        Empires empires = getEmpires(updateDockOrder);
//        List<Missiles> missiles = getMissiles(updateDockOrder);
            String uuid = updateDockOrder.getOrderKey();
        return updateOrderDao.updateAndReturnUpdate(uuid,weapons,shields,hulls);
    }

    public Weapons updateWeaponService(String orderId, String weapon) {

       Weapons weaponname =  updateOrderDao.fetchWeapons(weapon)
               .orElseThrow(() -> new NoSuchElementException("Model with ID=" + weapon));

        return updateOrderDao.updateWeapon(orderId,weaponname);
    }

    private Hulls getHulls(UpdateDockOrder updateDockOrder) {
        return updateOrderDao.fetchHulls(updateDockOrder.getHullName())
                .orElseThrow(() -> new NoSuchElementException("Hull with ID="
                + updateDockOrder.getHullName()));
    }

    private Shields getShields(UpdateDockOrder updateDockOrder) {
        return updateOrderDao.fetchShields(updateDockOrder.getShieldType())
                .orElseThrow(() -> new NoSuchElementException("Shield with ID="
                + updateDockOrder.getShieldType()));
    }


    private Weapons getWeapons(UpdateDockOrder updateDockOrder) {
       return updateOrderDao.fetchWeapons(updateDockOrder.getWeaponType())
                .orElseThrow(() -> new NoSuchElementException("Model with ID="
                        + updateDockOrder.getWeaponType()));
    }

    public String deleteService(String orderId){
        return updateOrderDao.deleteOrder(orderId);
    }


}
