package edu.lawrence.freecycleSystem.core;

import org.springframework.jdbc.core.RowMapper;

public class SiteRowMapper implements RowMapper<Site> {
    @Override
    public Site mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Site s = new Site();
        s.setSiteId(rs.getString("site_id"));
        s.setSiteName(rs.getString("site_name"));
        s.setAddress(rs.getString("address"));
        return s;
    }
};