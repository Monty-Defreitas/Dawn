package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.Empires;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.security.*;
import java.util.Objects;

@Component
@Slf4j
public class DefaultEmpireDAO implements CreateEmpireDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Empires createEmpire(Empires empire){
        try {
            SqlParams params = generateInsertEmpire(empire);

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(params.sql, params.source,keyHolder);

            long empirePK = Objects.requireNonNull(keyHolder.getKey()).longValue();

            return Empires.builder().empireId(Math.toIntExact(empirePK))
                    .empireName(empire.getEmpireName())
                    .sector(params.source.getValue("sector").toString())
                    .alliance(empire.getAlliance())
                    .build();

            } catch (Exception e) {
            e.printStackTrace();
        }

            return null;

    }


    private SqlParams generateInsertEmpire(Empires createEmpire) throws NoSuchAlgorithmException {

        SqlParams params = new SqlParams();

        // @formatter:off

        params.sql = "INSERT INTO empires ("
                + "empire_name, sector,alliance"
                + ") VALUES ("
                + ":empire_name, :sector, :alliance)";

        params.source.addValue("empire_name",createEmpire.getEmpireName());
        params.source.addValue("sector", hashCode(createEmpire.getSector()));
        params.source.addValue("alliance", createEmpire.getAlliance());

        return params;
    }




    class SqlParams {
        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();
    }
     //Look into BCrypt
    private StringBuilder hashCode(String sector) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        //Can also use MD5<--Dont use per Dr.Rob.
        md.update(sector.getBytes());

        byte[] result = md.digest();

        StringBuilder sb = new StringBuilder();

        for(byte b : result) {
            sb.append(String.format("%02x",b));
        }

        return sb;
    }

}
