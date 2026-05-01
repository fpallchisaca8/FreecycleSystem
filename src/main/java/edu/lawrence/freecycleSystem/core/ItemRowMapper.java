package edu.lawrence.freecycleSystem.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet row, int rowNum) throws SQLException {
        Item i = new Item();
        i.setItemId(row.getString("item_id"));
        i.setDonorId(row.getString("donor_id"));
        i.setTitle(row.getString("title"));
        i.setDescription(row.getString("description"));
        i.setImageurl(row.getString("imageurl"));
        i.setStatus(row.getString("status"));
        return i;
    }
}