package edu.lawrence.freecycleSystem.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User u = new User();
        u.setKey(row.getString("userid"));
        u.setName(row.getString("name"));
        u.setPassword(row.getString("password"));
        return u;
    }
}