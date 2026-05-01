package edu.lawrence.freecycleSystem.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TimeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveTime(Time time) {
        String sql = "INSERT INTO times (start_time, end_time, site_id, availability, donor_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, time.getStartTime(), time.getEndTime(), 
                           time.getSiteId(), time.getAvailability(), time.getDonorId());
    }

    public List<Time> findAvailableByDonor(String donorId) {
        String sql = "SELECT * FROM times WHERE donor_id = ? AND availability = 1";
        RowMapper<Time> rowMapper = new TimeRowMapper();
        try {
            return jdbcTemplate.query(sql, rowMapper, donorId);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public void markAsTaken(String timeId) {
        String sql = "UPDATE times SET availability = 0 WHERE time_id = ?";
        jdbcTemplate.update(sql, timeId);
    }
}