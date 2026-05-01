package edu.lawrence.freecycleSystem.core;
import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;


public class TagRowMapper implements RowMapper<tag> {
    @Override

    public tag mapRow(ResultSet row, int rowNum) throws java.sql.SQLException {
        tag t = new tag();
        t.setTagId(row.getInt("tag_id"));
        t.setItemId(row.getString("item_id"));
        t.setTagName(row.getString("tag_name"));
        return t;
    }
}
