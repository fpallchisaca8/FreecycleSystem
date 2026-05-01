package edu.lawrence.freecycleSystem.interfaces;

import edu.lawrence.freecycleSystem.core.Item;
import edu.lawrence.freecycleSystem.core.ItemDAO;
import edu.lawrence.freecycleSystem.core.Profile;
import edu.lawrence.freecycleSystem.core.User;
import edu.lawrence.freecycleSystem.core.UserDAO;
import edu.lawrence.freecycleSystem.interfaces.dtos.Donation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserDAO dao;
    private final ItemDAO itemDAO;

    public UserController(UserDAO dao,ItemDAO itemDAO) {
        this.dao = dao;
        this.itemDAO = itemDAO;
    }

    @PostMapping("/login")
    public ResponseEntity<String> checkLogin(@RequestBody User user) {
        User result = dao.findByNameAndPassword(user.getName(), user.getPassword());
        if (result == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user name or password");
        }
        return ResponseEntity.ok().body(result.getKey());
    }

    @PostMapping("/{id}/profile")
    public ResponseEntity<String> saveProfile(@PathVariable String id,@RequestBody Profile profile) {
    	String result = dao.saveProfile(id,profile);
        switch (result) {
            case "Bad Id":
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user id");
            case "Duplicate":
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate profile");
            case "Error":
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not store profile");
            default:
                break;
        }
    	return ResponseEntity.ok().body("Profile created");
    }
    
    @GetMapping("/{id}/profile")
    public ResponseEntity<Profile> getProfile(@PathVariable String id) {
    	Profile result = dao.findProfile(id);
    	if(result == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
    	return ResponseEntity.ok().body(result);
    }
    
	@GetMapping("/{id}/items")
	public ResponseEntity<List<Item>> getItems(@PathVariable String id) {
		List<Item> results = itemDAO.findByUser(id);
		return ResponseEntity.ok().body(results);
	}
	@GetMapping("/{id}/offers")
	public ResponseEntity<List<Item>> getOffers(@PathVariable String id) {
		List<Item> results = dao.findItemsToRecieve(id);
		return ResponseEntity.ok().body(results);
	}
    
    @GetMapping("/{id}/accepted")
    public ResponseEntity<List<Donation>> getDonations(@PathVariable String id) {
    	List<Donation> results = dao.findDonations(id);
    	return ResponseEntity.ok().body(results);
    }
    
    @PostMapping
    public ResponseEntity<String> save(@RequestBody User user) {
        if (user.getName().isBlank() || user.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty user name or password");
        }

        String key = dao.save(user);
        if (key.equals("Duplicate")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this name already exists");
        } else if (key.equals("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
        }
        return ResponseEntity.ok().body(key);
    }
}