package edu.lawrence.freecycleSystem.core;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class TagDAO {
    @Autowired
    private final JdbcTemplate jdbc;

    public TagDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Item> getItemsFromTag(String tag) {
        String sql = "SELECT * FROM tags WHERE tag_name=?";
        return jdbc.query(sql, new ItemRowMapper(), tag);
    }

    
    public void addTagToItem(String itemId, String tagName) {
        String sql = "INSERT INTO tags (item_id, tag_name) VALUES (?, ?)";
        jdbc.update(sql, itemId, tagName);
    }
     
}
