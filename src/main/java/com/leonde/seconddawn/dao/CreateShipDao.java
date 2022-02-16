package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class CreateShipDao implements CreateShipOrderDao {

    @Autowired
    JdbcTemplate jdbcTemplate2;


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public DockOrder saveDockOrder(Weapons weaponFk, Shields shieldFk, Empires empireFk,
                                   Hulls hullFk, List<Missiles> missileOptions,
                                   BigDecimal parsecks) {

        SqlParams params = generateInsertSql(weaponFk, shieldFk, empireFk, hullFk, parsecks);

        UUID uuid = UUID.randomUUID();

        SqlParams paramsUUID = new SqlParams();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(params.sql, params.source, keyHolder);

        Long orderPk = Objects.requireNonNull(keyHolder.getKey()).longValue();

        paramsUUID.sql = "UPDATE dock_order set some_key = :some_key WHERE dock_id = :dock_id";

        String identifier = uuid.toString();
        paramsUUID.source.addValue("some_key", identifier);
        paramsUUID.source.addValue("dock_id", keyHolder.getKey());

        jdbcTemplate.update(paramsUUID.sql, paramsUUID.source, keyHolder);

        saveOptions(missileOptions, orderPk);

        return DockOrder.builder().message("hold onto your order key so you can update your order")
                .orderKey(identifier).empireFk(empireFk).weaponFk(weaponFk).shieldFk(shieldFk)
                .hullFk(hullFk)
                .missileOptions(missileOptions).parsecks(parsecks)
                .build();
    }


    private void saveOptions(List<Missiles> options, Long orderPK) {
        for (Missiles option : options) {
            SqlParams params = generateInsertSql(option, orderPK);
            jdbcTemplate.update(params.sql, params.source);
        }
    }

    private SqlParams generateInsertSql(Missiles option, Long orderPK) {
        SqlParams params = new SqlParams();

        // @formatter:off
        params.sql = ""
                + "INSERT INTO missile_dock_order ("
                + "missile_id_fk, dock_id_fk"
                + ") VALUES ("
                + ":missile_id_fk, :dock_id_fk"
                + ")";
        // @formatter:on

        params.source.addValue("missile_id_fk", option.getMissileId());
        params.source.addValue("dock_id_fk", orderPK);

        return params;
    }

    private SqlParams generateInsertSql(Weapons weaponFk, Shields shieldFk, Empires empireFk,
                                        Hulls hullFk, BigDecimal parsecks) {
        // @formatter:off
        String sql = ""
                + "INSERT INTO dock_order ("
                + "weapon_FK, shield_FK, empire_id, hull_id, parsecks"
                + ") VALUES ("
                + ":weapon_Fk, :shield_Fk, :empire_id, :hull_id,:parsecks"
                + ")";
        // @formatter:on


        SqlParams params = new SqlParams();

        params.sql = sql;

        params.source.addValue("weapon_Fk", weaponFk.getWeaponId());
        params.source.addValue("shield_Fk", shieldFk.getShieldId());
        params.source.addValue("empire_id", empireFk.getEmpireId());
        params.source.addValue("hull_id", hullFk.getHullId());
        params.source.addValue("parsecks", parsecks);

        return params;
    }


    @Override
    public List<Missiles> fetchOptions(List<String> missileId) {
        if (missileId.isEmpty()) {
            return new LinkedList<>();
        }

        Map<String, Object> params = new HashMap<>();

        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM missiles "
                + "WHERE missile_name IN(";
        // @formatter:on

        for (int index = 0; index < missileId.size(); index++) {
            //122-123 line creates the Key for the Param Map
            String key = "missile_" + index;
            sql += ":" + key + ", ";
            //This line inserts a key: value pair into the map.
            // The Value is whatever Object is at the index of the param List.
            params.put(key, missileId.get(index));
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += ")";

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> Missiles.builder()
                .missileId(rs.getInt("missile_id"))
                .energyRequirements(rs.getInt("energy_requirements"))
                .combatSpeed(rs.getInt("combat_speed"))
                .missileName(rs.getString("missile_name"))
                .damageOutput(rs.getInt("damage_output"))
                .build());
    }

    /**
     *
     */
    @Override
    public Optional<Empires> fetchEmpire(String empireId) {
        // @formatter:off
        String sql = "SELECT * "
                + "FROM empires "
                + "WHERE empire_name = :empire_name";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("empire_name", empireId);

        return Optional.ofNullable((jdbcTemplate.query(sql, params, new EmpireResultSetExtractor())));
    }


    @Override
    public Optional<Hulls> fetchHulls(String hullId) {
        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM hulls "
                + "WHERE hull_name = :hull_name ";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("hull_name", hullId);

        return Optional.ofNullable((jdbcTemplate.query(sql, params, new HullsResultSetExtractor())));
    }


    @Override
    public Optional<Shields> fetchShields(String shield_type) {
        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM shields "
                + "WHERE shield_type = :shield_type";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("shield_type", shield_type);

        return Optional.ofNullable(jdbcTemplate.query(sql, params, new ShieldsResultSetExtractor()));
    }


    @Override
    public Optional<Weapons> fetchWeapons(String weapon_name) {
        // @formatter:off
        String sql = ""
                + "SELECT * "
                + "FROM weapons "
                + "WHERE weapon_name = :weapon_name";
        // @formatter:on

        Map<String, Object> params = new HashMap<>();
        params.put("weapon_name", weapon_name);

        return Optional.ofNullable(jdbcTemplate.query(sql, params, new WeaponsResultSetExtractor()));
    }


    public List<Weapons> fetchAllWeapons() {

        return jdbcTemplate.query(
                "SELECT weapon_name, energy_requirements, weapon_damage, parsecks" +
                        " FROM weapons",
                (rs, rowNum) -> Weapons.builder()
                        .weaponName(rs.getString("weapon_name"))
                        .energyRequirements(rs.getInt("energy_Requirements"))
                        .parsecks(rs.getBigDecimal("parsecks"))
                        .weaponDamage(rs.getInt("weapon_damage"))
                        .build());
    }

    public List<Shields> fetchAllShield() {
        return jdbcTemplate.query(
                "SELECT shield_type, energy_requirements, parsecks, damage_mitigation" +
                        " FROM shields", ((rs, rowNum) -> Shields.builder()
                        .damageMitigation(rs.getInt("damage_mitigation"))
                        .energyRequirements(rs.getInt("energy_requirements"))
                        .parsecks(rs.getBigDecimal("parsecks"))
                        .shieldType(rs.getString("shield_Type"))
                        .build()));
    }

    public List<Hulls> fetchAllHulls() {
        return jdbcTemplate.query("SELECT hull_name, hull_resilience, energy_requirements, combat_speed, parsecks" +
                " FROM hulls", ((rs, rowNum) -> Hulls.builder()
                .hullName(rs.getString("hull_name"))
                .hullResilience(rs.getInt("hull_resilience"))
                .energyRequirements(rs.getInt("energy_requirements"))
                .combatSpeed(rs.getInt("combat_speed"))
                .parsecks(rs.getBigDecimal("parsecks"))
                .build()));
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


    class SqlParams {
        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();
    }
}
