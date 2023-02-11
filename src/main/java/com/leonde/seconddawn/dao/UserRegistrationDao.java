package com.leonde.seconddawn.dao;

import com.leonde.seconddawn.entity.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public String registerUser(UserRegistration user){

        SqlParams params = generateInsertUser(user);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(params.sql, params.source,keyHolder);

        return "Successfully Registered";
    }
    private SqlParams generateInsertUser(UserRegistration userRegistration) {

            SqlParams params = new SqlParams();

        // @formatter:off

        params.sql = "INSERT INTO users ("
                + "username,password"
                + ") VALUES ("
                + ":username, :password)";

        String newPassword = String.valueOf(userRegistration.getPassword().hashCode());

        params.source.addValue("username",userRegistration.getUsername());
        params.source.addValue("password", newPassword);

        return params;
    }

    class SqlParams {
        String sql;
        MapSqlParameterSource source = new MapSqlParameterSource();
    }
}
