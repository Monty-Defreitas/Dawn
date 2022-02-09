package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.Empires;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class DefaultEmpireDAO implements CreateEmpireDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Empires createEmpire(Empires empire){
        SqlParams params = generateInsertEmpire(empire);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(params.sql, params.source,keyHolder);

        long empirePK = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return Empires.builder().empireId(Math.toIntExact(empirePK))
                .empireName(empire.getEmpireName())
                .sector(empire.getSector())
                .alliance(empire.getAlliance())
                .build();
    }


    private SqlParams generateInsertEmpire(Empires createEmpire){

        SqlParams params = new SqlParams();

        // @formatter:off

        params.sql = "INSERT INTO empires ("
                + "empire_name, sector,alliance"
                + ") VALUES ("
                + ":empire_name, :sector, :alliance)";

        params.source.addValue("empire_name",createEmpire.getEmpireName());
        params.source.addValue("sector", createEmpire.getSector());
        params.source.addValue("alliance", createEmpire.getAlliance());

        return params;
    }




    class SqlParams {
        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();
    }

}
