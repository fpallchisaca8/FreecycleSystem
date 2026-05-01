package edu.lawrence.freecycleSystem.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.lawrence.freecycleSystem.interfaces.dtos.Donation;
import edu.lawrence.freecycleSystem.services.PasswordService;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired 
    PasswordService passwordService;
    
    public User findByNameAndPassword(String name,String password) {
    	String sql = "SELECT * FROM users WHERE name=?";
        RowMapper<User> rowMapper = new UserRowMapper();
        User result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, rowMapper, name);
        } catch(Exception ex) {
            
        }
        if(result != null && passwordService.verifyHash(password, result.getPassword())) {
        	result.setPassword("Undisclosed");
        } else {
        	result = null;
        }
        return result;	
    }

	public String save(User user) {
	    // First make sure this is not a duplicate
	    String sql = "SELECT * FROM users WHERE name=?";
	    RowMapper<User> rowMapper = new UserRowMapper();
	    User old = null;
	    try {
	        old = jdbcTemplate.queryForObject(sql, rowMapper, user.getName());
	    } catch(Exception ex) {
	
	    }
	    if(old != null)
	        return "Duplicate";
	
	    // Have MySQL generate a unique id
	    String idSQL = "select uuid()";
	    String key;
	    try {
	        key = jdbcTemplate.queryForObject(idSQL, String.class);
	    } catch(Exception ex) {
	        key = "Error";
	    }
	    if(key.equals("Error"))
	        return key;
	
	    String hash = passwordService.hashPassword(user.getPassword());
	    String insertSQL = "insert into users(userid,name,password) values (?, ?, ?)";
	    jdbcTemplate.update(insertSQL,key,user.getName(),hash);
	    return key;
	}
    
    public String saveProfile(String userid,Profile profile) {
    	// Check for valid user id
    	if(confirmUser(userid) == false)
    		return "Bad Id";
    	
    	// Check for duplicates
    	Profile existing = null;
    	String checkSQL = "SELECT * FROM profile where user_id=?";
    	RowMapper<Profile> rowMapper = new ProfileRowMapper();
        try {
            existing = jdbcTemplate.queryForObject(checkSQL, rowMapper, userid);
        } catch(Exception ex) {

        }
        if(existing != null)
        	return "Duplicate";
              
        String profileSQL = "insert into profile(user,fullname,email,phone,general_location,bio,public) values(?,?,?,?,?,?,?)";
        try {
        	jdbcTemplate.update(profileSQL,userid,profile.getFullname(),profile.getEmail(),
        			profile.getPhone(),profile.getGeneral_location(),profile.getBio(),profile.getIspublic());
        } catch(Exception ex) {
        	ex.printStackTrace();
        	return "Error";
        }
    	return "OK";
    }
    
    public Profile findProfile(String userId) {
    	String sql = "select * from profile where user=?";
    	RowMapper<Profile> rowMapper = new ProfileRowMapper();
        Profile result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, rowMapper, userId);
        } catch(Exception ex) {
            
        }
        if(result == null){
			return null;
		}
		return result;
    }
    
	public List<Item> findItemsToRecieve(String userId) {
		String sql = "select * from items where recipient_id=? and status='Pending'";
		RowMapper<Item> rowMapper = new ItemRowMapper();
		try {
			return jdbcTemplate.query(sql, rowMapper,userId);
		} catch(Exception ex) {
			return new ArrayList<>();
		}
	}
    
   public List<Donation> findDonations(String userId) {
    	String sql = "select * from items where user_id=? AND (status='Pending' OR status='Scheduled)";
	    List<Item> items;
    	try {
        	items = jdbcTemplate.query(sql, new ItemRowMapper(), userId);
	    } catch(Exception ex) {
	        items = new ArrayList<>();
    }

    List<Donation> results = new ArrayList<>();
    for(Item item : items) {
		Donation d = new Donation(item.getItemId(), item.getItemId(), item.getTitle());
        results.add(d);
    }
    return results;
    }

    private boolean confirmUser(String userid) {
    	String sql = "SELECT * FROM users WHERE userid=?";
        RowMapper<User> rowMapper = new UserRowMapper();
        User old = null;
        try {
            old = jdbcTemplate.queryForObject(sql, rowMapper, userid);
        } catch(Exception ex) {

        }
        return (old != null);
    }
}