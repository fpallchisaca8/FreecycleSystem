package edu.lawrence.freecycleSystem.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycleSystem.interfaces.dtos.OfferResponse;


@Repository
public class TransferDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //change offers to another table?
	public void saveSelection(String itemId, String recipientId) {
		String sql = "update offers set status='Pending', recipient_id=? where item_id=?";
		jdbcTemplate.update(sql, recipientId, itemId);
	}

	public void saveSchedule(String itemId, String location, String time) {
        String sqlMeeting = "insert into meetings (item_id, location, time) values (?, ?, ?)";
        jdbcTemplate.update(sqlMeeting, itemId, location, time);
        
        String sqlStatus = "update offers set status='Scheduled' where item_id=?";
        jdbcTemplate.update(sqlStatus, itemId);
    }

    public boolean saveScheduleResponse(OfferResponse or) {
        if (!or.getAccept()) {
            cancelSelection(or.getTransferId()); 
            return false;
        }

        String sqlMeeting = "INSERT INTO meetings (item_id, site_id, time_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlMeeting, or.getTransferId(), or.getSiteId(), or.getTimeId());
        
        String sqlStatus = "UPDATE items SET status='Scheduled' WHERE item_id=?";
        jdbcTemplate.update(sqlStatus, or.getTransferId());
        
        return true;
    }

	public void completeTransfer(String itemId) {
        String sql = "update offers set status='Completed' where item_id=?";
        jdbcTemplate.update(sql, itemId);
    }

	public void cancelSelection(String itemId){
		String deleteMeetingSQL = "delete from meetings where item_id = ?";
	    jdbcTemplate.update(deleteMeetingSQL, itemId);

		String updateOfferSQL = "update offers set status = 'Posted', recipient_id = NULL where item_id = ?";
    	jdbcTemplate.update(updateOfferSQL, itemId);
	}

	public void withdrawInterest(String itemId, String recipientId) {
    	String deleteInterestSQL = "delete from interests where item_id = ? and user_id = ?";
	    jdbcTemplate.update(deleteInterestSQL, itemId, recipientId);

    	String deleteMeetingSQL = "delete from meetings where item_id = ?";
    	jdbcTemplate.update(deleteMeetingSQL, itemId);

    	String updateOfferSQL = "update offers set status = 'Posted', recipient_id = NULL where item_id = ?";
    	jdbcTemplate.update(updateOfferSQL, itemId);
	}

	public void cancelOffer(String itemId) {
        jdbcTemplate.update("delete from meetings where item_id = ?", itemId);
        jdbcTemplate.update("delete from interests where item_id = ?", itemId);
        jdbcTemplate.update("delete from offers where item_id = ?", itemId);
    }
}
