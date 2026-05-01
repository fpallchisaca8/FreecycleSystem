package edu.lawrence.freecycleSystem.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TimeRowMapper implements RowMapper<Time> {
    @Override
    public Time mapRow(ResultSet row, int rowNum) throws SQLException {
        Time t = new Time();
        
        t.setTimeId(row.getString("time_id"));
        t.setStartTime(row.getInt("start_time"));
        t.setEndTime(row.getInt("end_time"));
        t.setSiteId(row.getString("site_id"));
        t.setAvailability(row.getInt("availability"));
        t.setDonorId(row.getString("donor_id"));
        
        return t;
    }
}