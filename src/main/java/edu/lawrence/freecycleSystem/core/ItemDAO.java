package edu.lawrence.freecycleSystem.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class ItemDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String save(Item item) {
		// Have MySQL generate a unique id
		String idSQL = "select uuid()";
		String key;
		try {
			key = jdbcTemplate.queryForObject(idSQL, String.class);
		} catch (Exception ex) {
			key = "Error";
		}
		if (key.equals("Error"))
			return key;

		String insertSQL = "insert into items(item_id, donor_id, title, description, imageurl, status) values (?, ?, ?, ?, ?, 'Posted') ";
		jdbcTemplate.update(insertSQL, key, item.getDonorId(), item.getTitle(), item.getDescription(),
				item.getImageurl());

		for (String tag : item.getTags()) {
			String tagSQL = "insert into tags(itemid,tag) values (?,?)";
			jdbcTemplate.update(tagSQL, key, tag);
		}
		return key;
	}

	public Item findById(String id) {
		String sql = "select * from items where item_id=?";
		Item result = null;
		RowMapper<Item> rowMapper = new ItemRowMapper();
		try {
			result = jdbcTemplate.queryForObject(sql, rowMapper, id);
			String tagSQL = "select tag from tags where itemid=?";
			List<String> tags = jdbcTemplate.queryForList(tagSQL, String.class, id);
			result.setTags(tags);
		} catch (Exception ex) {
		}

		return result;
	}

	public List<Item> findByUser(String userid) {
		String sql = "select * from items where donor=?"; //changed to donor_id
		List<Item> results;
		RowMapper<Item> rowMapper = new ItemRowMapper();
		try {
			results = jdbcTemplate.query(sql, rowMapper, userid);
		} catch (Exception ex) {
			results = new ArrayList<>();
		}
		for (Item i : results) {
			String tagSQL = "select tag from tags where itemid=?";
			List<String> tags = jdbcTemplate.queryForList(tagSQL, String.class, i.getItemId());
			i.setTags(tags);
		}
		return results;
	}

	public List<Item> findActive() {
		String sql = "select * from items where status='Posted'";
		List<Item> results;
		RowMapper<Item> rowMapper = new ItemRowMapper();
		try {
			results = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception ex) {
			results = new ArrayList<>();
		}
		return results;
	}
/* 
	public ArrayList<Item> findActiveByTag(String tag) {
		ArrayList<Item> results = new ArrayList<>();

		List<String> ids;
		String tagSQL = "select item_id from tags where tag=?";
		try {
			ids = jdbcTemplate.queryForList(tagSQL, String.class, tag);
		} catch (Exception ex) {
			ids = new ArrayList<>();
		}

		for (String id : ids) {
			RowMapper<Item> rowMapper = new ItemRowMapper();
			String interestSQL = "select * from items where item_id=? and opens<=curdate() and closes>=curdate()";
			Item item = null;
			try {
				item = jdbcTemplate.queryForObject(interestSQL, rowMapper, id);
			} catch (Exception ex) {

			}
			if (item != null)
				results.add(item);
		}
		return results;
	}

	*/

	public void saveInterest(String itemid, Interest interestid) {
		String insertSQL = "insert into interests(item,interested_user,entered) values(?,?,curtime())";
		jdbcTemplate.update(insertSQL, itemid, interestid.getInterestedUser());
	}

	public List<Interest> findInterestsForItem(String itemid) {
		String sql = "select * from interests where item=?";
		List<Interest> results;
		RowMapper<Interest> rowMapper = new InterestRowMapper();
		try {
			results = jdbcTemplate.query(sql, rowMapper, itemid);
		} catch (Exception ex) {
			results = new ArrayList<>();
		}
		return results;
	}
}
