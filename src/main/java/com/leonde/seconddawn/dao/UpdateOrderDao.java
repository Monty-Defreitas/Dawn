package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.DockOrder;
import com.leonde.seconddawn.entity.Weapons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.UUID;

public class UpdateOrderDao {

    @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

    public DockOrder updateAndReturnUpdate(String identifier){

        String sql = "UPDATE dock_order set weapon_FK = :weapon_fk WHERE some_key = :some_key";

        UUID uuid = UUID.randomUUID();
        String identifier2 = uuid.toString();

        SqlParameterSource namedParameters = new MapSqlParameterSource("some_key", identifier);
          jdbcTemplate.update(sql,namedParameters);

        return DockOrder.builder().build();
    }


}
