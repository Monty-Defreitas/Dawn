package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class ReadOrderDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public DockOrder returnOrderById(String orderId) {

        String sql = "SELECT * " + " "
                + "FROM dock_order "
                + "WHERE some_key = :some_key";

        Map<String, Object> params = new HashMap<>();
        params.put("some_key", orderId);

        ReturnDockOrder dockOrder = jdbcTemplate.query(sql,params,new DockOrderResultSetExtractor());


        log.info("my Order deets = {}",dockOrder);
        System.out.println(dockOrder.toString());

        Empires empires = fetchEmpire(dockOrder.getEmpireFk());
        Hulls hulls = fetchHulls(dockOrder.getHullFk());
        Shields shields =fetchShields(dockOrder.getShieldFk());
        Weapons weapons = fetchWeapons(dockOrder.getWeaponFk());
        BigDecimal parsecks = hulls.getParsecks().add(shields.getParsecks()).add(weapons.getParsecks());

        return DockOrder.builder().empireFk(empires).hullFk(hulls).shieldFk(shields).weaponFk(weapons).parsecks(parsecks).build();
    }


    class  DockOrderResultSetExtractor implements  ResultSetExtractor<ReturnDockOrder> {

        public ReturnDockOrder extractData(ResultSet rs) throws SQLException {
            rs.next();
            return ReturnDockOrder.builder().orderKey(rs.getString("some_key"))
                    .shieldFk(rs.getString("shield_FK"))
                    .hullFk(rs.getString("hull_id"))
                    .weaponFk(rs.getString("weapon_FK"))
                    .empireFk(rs.getString("empire_id"))
                    .parsecks(rs.getBigDecimal("parsecks"))
                    .build();
        }
    }

    public Empires fetchEmpire(String empireId) {
        // @formatter:off
        String sql = "SELECT * "
                + "FROM empires "
                + "WHERE empire_id = :empire_name";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("empire_name", empireId);

        return jdbcTemplate.query(sql, params, new EmpireResultSetExtractor());
    }


    public Hulls fetchHulls(String hullId) {
        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM hulls "
                + "WHERE hull_id = :hull_name ";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("hull_name", hullId);

        return jdbcTemplate.query(sql, params, new HullsResultSetExtractor());
    }


    public Shields fetchShields(String shield_type) {
        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM shields "
                + "WHERE shield_id = :shield_type";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("shield_type", shield_type);

        return jdbcTemplate.query(sql, params, new ShieldsResultSetExtractor());
    }


    public Weapons fetchWeapons(String weapon_name) {
        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM weapons "
                + "WHERE weapon_id = :weapon_name";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("weapon_name", weapon_name);

        return jdbcTemplate.query(sql, params, new WeaponsResultSetExtractor());
    }

    class WeaponsResultSetExtractor implements ResultSetExtractor<Weapons> {
        @Override
        public Weapons extractData(ResultSet rs) throws SQLException {
            rs.next();

            // @formatter:off
            return Weapons.builder().weaponId(rs.getInt("weapon_id"))
                    .weaponName(rs.getString("weapon_name"))
                    .energyRequirements(rs.getInt("energy_Requirements"))
                    .parsecks(rs.getBigDecimal("parsecks"))
                    .weaponDamage(rs.getInt("weapon_damage"))
                    .build();
            // @formatter:on
        }
    }


    class ShieldsResultSetExtractor implements ResultSetExtractor<Shields> {
        @Override
        public Shields extractData(ResultSet rs) throws SQLException {
            rs.next();

            // @formatter:off
            return Shields.builder()
                    .damageMitigation(rs.getInt("damage_mitigation"))
                    .energyRequirements(rs.getInt("energy_requirements"))
                    .parsecks(rs.getBigDecimal("parsecks"))
                    .shieldType(rs.getString("shield_Type"))
                    .shieldId(rs.getInt("shield_id"))
                    .build();
            // @formatter:on
        }
    }


    class HullsResultSetExtractor implements ResultSetExtractor<Hulls> {
        @Override
        public Hulls extractData(ResultSet rs) throws SQLException {
            rs.next();


            return Hulls.builder()
                    .hullId(rs.getInt("hull_id"))
                    .hullName(rs.getString("hull_name"))
                    .hullResilience(rs.getInt("hull_resilience"))
                    .energyRequirements(rs.getInt("energy_requirements"))
                    .combatSpeed(rs.getInt("combat_speed"))
                    .parsecks(rs.getBigDecimal("parsecks"))
                    .build();
        }
    }


    class EmpireResultSetExtractor implements ResultSetExtractor<Empires> {
        @Override
        public Empires extractData(ResultSet rs) throws SQLException {
            rs.next();

            // @formatter:off
            return Empires.builder().empireId(rs.getInt("empire_id"))
                    .empireName(rs.getString("empire_name"))
                    .sector(rs.getString("sector"))
                    .alliance(rs.getString("alliance"))
                    .build();
            // @formatter:on

        }
    }


}
