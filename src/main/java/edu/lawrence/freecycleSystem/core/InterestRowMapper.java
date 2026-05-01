package edu.lawrence.freecycleSystem.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InterestRowMapper implements RowMapper<Interest> {
    @Override
    public Interest mapRow(ResultSet row, int rowNum) throws SQLException {
        Interest i = new Interest();
        i.setInterestId(row.getInt("interestid"));
        i.setItemId(row.getString("item"));
        i.setInterestedUser(row.getString("interested_user"));
        i.setEntered(row.getString("entered"));
        return i;
    }
}