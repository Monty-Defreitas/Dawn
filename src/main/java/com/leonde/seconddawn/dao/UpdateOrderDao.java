package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.Hulls;
import com.leonde.seconddawn.entity.Shields;
import com.leonde.seconddawn.entity.Weapons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Component
public class UpdateOrderDao extends CreateShipDao{

    @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

    public DockOrder updateAndReturnUpdate(String identifier, Weapons weapons, Shields shields, Hulls hulls){

        String sql = "UPDATE dock_order set weapon_FK = :weapon_fk, " +
                     "shield_FK  = :shield_fk, hull_id = :hull_id, " + " parsecks = :parsecks " +
                     "WHERE some_key = :some_key";


        BigDecimal price = weapons.getParsecks().add(shields.getParsecks()).add(hulls.getParsecks());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
             namedParameters.addValue("some_key", identifier);
             namedParameters.addValue("weapon_fk", weapons.getWeaponId());
             namedParameters.addValue("shield_fk", shields.getShieldId());
             namedParameters.addValue("hull_id", hulls.getHullId());
             namedParameters.addValue("parsecks", price);


          int status = jdbcTemplate.update(sql,namedParameters);

            if (status != 1) {
                throw new NoSuchElementException("This order does not exist for update");
            }


        System.out.println(status);

        return DockOrder.builder().orderKey(identifier)
                .weaponFk(weapons).shieldFk(shields)
                .hullFk(hulls).parsecks(price).build();
    }


    public Weapons updateWeapon(String orderId, Weapons weapons) {

       String sql = " UPDATE dock_order set weapon_FK = :weapon_fk  WHERE some_key = :some_key";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("some_key", orderId);
        namedParameters.addValue("weapon_fk", weapons.getWeaponId());

                int status = jdbcTemplate.update(sql,namedParameters);

                if (status != 1){
                    throw new NoSuchElementException("This order does not exist for update");
                }

        return Weapons.builder().weaponName(weapons.getWeaponName())
                .weaponDamage(weapons.getWeaponDamage())
                .energyRequirements(weapons.getEnergyRequirements())
                .parsecks(weapons.getParsecks())
                .build();
    }

    public Shields updateShield(String orderId, Shields shields) {

        String sql = " UPDATE dock_order set shield_FK = :shield_fk, some_key = :some_key";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("some_key", orderId);
        namedParameters.addValue("shield_fk", shields.getShieldId());

        int status = jdbcTemplate.update(sql,namedParameters);

        if (status != 1){
            throw new NoSuchElementException("This order does not exist for update");
        }

        return Shields.builder().shieldType(shields.getShieldType())
                .build();
    }

    public Hulls updateHull(String orderId, Hulls hulls) {

        String sql = " UPDATE dock_order set hull_id = :hull_id, some_key = :some_key";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("some_key", orderId);
        namedParameters.addValue("hull_id", hulls.getHullId());

        int status = jdbcTemplate.update(sql,namedParameters);

        if (status != 1){
            throw new NoSuchElementException("This order does not exist for update");
        }

        return Hulls.builder().hullName(hulls.getHullName())
                .hullResilience(hulls.getHullResilience())
                .combatSpeed(hulls.getCombatSpeed())
                .energyRequirements(hulls.getEnergyRequirements())
                .parsecks(hulls.getParsecks())
                .build();
    }



   public String deleteOrder(String orderId) {

        SqlStuff params = new SqlStuff();

         params.sql = "DELETE from dock_order where some_key = :some_key";

         params.namedParameter = new MapSqlParameterSource("some_key", orderId);

         int status = jdbcTemplate.update(params.sql, params.namedParameter);

        if(status != 0) {
            return  "Order data deleted for ID " + orderId;
       }else{
           throw new NoSuchElementException("No Order found with ID " + orderId);
        }
}

       class SqlStuff {
       String sql;
       SqlParameterSource namedParameter;
        }
}
