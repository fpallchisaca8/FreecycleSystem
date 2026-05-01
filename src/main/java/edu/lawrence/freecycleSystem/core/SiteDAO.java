package edu.lawrence.freecycleSystem.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SiteDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Site> findAllSites() {
        RowMapper<Site> rowMapper = new SiteRowMapper();
        String sql = "SELECT * FROM sites";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Site findSiteById(String siteId) {
        String sql = "SELECT * FROM sites WHERE site_id = ?";
        RowMapper<Site> rowMapper = new SiteRowMapper();
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, siteId);
        } catch (Exception e) {
            return null;
        }
    }

    public void insertSite(Site site) {
        String sql = "INSERT INTO sites (site_name, address) VALUES (?, ?)";
        jdbcTemplate.update(sql, site.getSiteName(), site.getAddress());
    }

    public void updateSite(Site site) {
        String sql = "UPDATE sites SET site_name = ?, address = ? WHERE site_id = ?";
        jdbcTemplate.update(sql, site.getSiteName(), site.getAddress(), site.getSiteId());
    }

    public void deleteSite(String siteId) {
        String sql = "DELETE FROM sites WHERE site_id = ?";
        jdbcTemplate.update(sql, siteId);
    }
}