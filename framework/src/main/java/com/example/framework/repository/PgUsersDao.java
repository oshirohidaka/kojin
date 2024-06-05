package com.example.framework.repository;

import com.example.framework.entity.ProductRecord;
import com.example.framework.entity.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class PgUsersDao implements UsersDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<UsersRecord> findAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                new DataClassRowMapper<>(UsersRecord.class));
    }

    @Override
    public UsersRecord findById(String login_id) {
        var param = new MapSqlParameterSource();
        param.addValue("login_id", login_id);
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :login_id ", param, new DataClassRowMapper<>(UsersRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }
}
