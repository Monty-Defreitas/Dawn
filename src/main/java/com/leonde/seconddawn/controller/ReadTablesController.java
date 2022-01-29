package com.leonde.seconddawn.controller;

import com.leonde.seconddawn.dao.CreateShipDao;
import com.leonde.seconddawn.entity.Hulls;
import com.leonde.seconddawn.entity.Items;
import com.leonde.seconddawn.entity.Shields;
import com.leonde.seconddawn.entity.Weapons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReadTablesController implements ReadTablesInterface{
    @Autowired
    CreateShipDao createShipDao;

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


}
