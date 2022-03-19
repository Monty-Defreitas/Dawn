package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.dao.CreateShipDao;
import com.leonde.seconddawn.dao.ReadOrderDao;
import com.leonde.seconddawn.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
public class ReadTablesController implements ReadTablesInterface {
    @Autowired
    CreateShipDao createShipDao;

    @Autowired
    ReadOrderDao readOrderDao;


    @Override
    public List<Weapons> readWeaponGoods() {
        return createShipDao.fetchAllWeapons();
    }

    @Override
    public List<Shields> readShieldGoods() {
        return createShipDao.fetchAllShield();
    }

    @Override
    public List<Hulls> readHullGoods() {
        return createShipDao.fetchAllHulls();
    }

    @Override
    public List<Missiles> readMissileGoods() {
        return createShipDao.fetchAllMissiles();
    }

    @Override
    public DockOrder readDockedOrders(String orderId) {

        return readOrderDao.returnOrderById(orderId);
    }


}
