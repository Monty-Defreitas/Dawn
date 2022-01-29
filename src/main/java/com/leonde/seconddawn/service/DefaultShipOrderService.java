package com.leonde.seconddawn.service;



import com.leonde.seconddawn.dao.CreateShipDao;
import com.leonde.seconddawn.dao.CreateShipOrderDao;
import com.leonde.seconddawn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class DefaultShipOrderService implements CreateShipOrderService{


    @Autowired
    CreateShipOrderDao createShipOrderDao;
    // TODO: 1/29/2022 Come back and delete createShipDao autowire if found to be obsolete.
    @Autowired
    CreateShipDao createShipDao;

    @Transactional


    @Override
    public DockOrder createOrder(DockOrderRequest orderRequest) {
        Weapons weapons = getWeapons(orderRequest);
        Shields shields = getShields(orderRequest);
        Hulls hulls = getHulls(orderRequest);
        Empires empires = getEmpires(orderRequest);
        List<Missiles> missiles = getMissiles(orderRequest);

        BigDecimal parsecks = weapons.getParsecks().add(shields.getParsecks()).add(hulls.getParsecks());


        return createShipOrderDao.saveDockOrder(weapons,shields,empires,hulls,missiles,parsecks);
    }


    private List<Missiles> getMissiles(DockOrderRequest orderRequest) {
        return createShipOrderDao.fetchOptions(orderRequest.getMissiles());
    }

    /**
     *
     * @param orderRequest
     * @return
     */
    private Empires getEmpires(DockOrderRequest orderRequest) {
        return createShipOrderDao.fetchEmpire(orderRequest.getEmpireName())
                .orElseThrow(() -> new NoSuchElementException(
                        "Empire with name=" + orderRequest.getEmpireName() + " was not found"));
    }

    /**
     *
     * @param orderRequest
     * @return
     */
    private Hulls getHulls(DockOrderRequest orderRequest) {
        return createShipOrderDao.fetchHulls(orderRequest.getHullName())
                .orElseThrow(() -> new NoSuchElementException(
                        "Engine with ID=" + orderRequest.getHullName() + " was not found"));
    }

    /**
     *
     * @param orderRequest
     * @return
     */
    private Shields getShields(DockOrderRequest orderRequest) {
        return createShipOrderDao.fetchShields(orderRequest.getShieldType())
                .orElseThrow(() -> new NoSuchElementException(
                        "Color with ID=" + orderRequest.getShieldType() + " was not found"));
    }

    /**
     *
     * @param orderRequest
     * @return
     */
    private Weapons getWeapons(DockOrderRequest orderRequest) {
        return createShipOrderDao
                .fetchWeapons(orderRequest.getWeaponType())
                .orElseThrow(() -> new NoSuchElementException("Model with ID="
                        + orderRequest.getWeaponType()));
    }

}
