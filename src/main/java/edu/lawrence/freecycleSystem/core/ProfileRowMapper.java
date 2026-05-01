package edu.lawrence.freecycleSystem.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProfileRowMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet row, int rowNum) throws SQLException {
        Profile p = new Profile();
        p.setBio(row.getString("bio"));
        p.setEmail(row.getString("email"));
        p.setFullname(row.getString("fullname"));
        p.setPhone(row.getString("phone"));
        p.setIspublic(row.getBoolean("public"));
        p.setGeneral_location(row.getString("general_location"));
        p.setUser(row.getString("user"));
        return p;
    }
}