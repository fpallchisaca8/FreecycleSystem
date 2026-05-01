package edu.lawrence.freecycleSystem.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TransferRowMapper implements RowMapper<Transfer> {
    @Override
    public Transfer mapRow(ResultSet row, int rowNum) throws SQLException {
        Transfer t = new Transfer();
        t.setItemId(row.getString("item_id"));
        t.setDonerId(row.getString("doner_id"));
        t.setRecipientId(row.getString("recipient_id"));
        t.setStatus(row.getString("status"));
        t.setSiteName(row.getString("site_name"));
        t.setTimeSlot(row.getString("time_slot"));
        return t;
    }
}