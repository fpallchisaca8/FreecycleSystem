package edu.lawrence.freecycleSystem.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.lawrence.freecycleSystem.core.TagDAO;
import edu.lawrence.freecycleSystem.core.Item;
import edu.lawrence.freecycleSystem.core.ItemDAO;
import edu.lawrence.freecycleSystem.core.Interest;
import edu.lawrence.freecycleSystem.core.tag;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*")
public class ItemController {
	private final ItemDAO dao;
	private final TagDAO tagDao;

    public ItemController(ItemDAO dao, TagDAO tagDao) {
        this.dao = dao;
		this.tagDao = tagDao;
    }

    
	@PostMapping
    public ResponseEntity<String> save(@RequestBody Item item) {
		String key = dao.save(item);
        if (key.equals("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can not generate key");
        }
        return ResponseEntity.ok().body(key);
	}
	
	@PostMapping("/{id}/interests")
    public void saveInterest(@PathVariable String id,@RequestBody Interest interest) {
		dao.saveInterest(id, interest);
	}

	
	
	@GetMapping("/{id}/interests")
    public ResponseEntity<List<Interest>> findInterestsForItem(@PathVariable String id) {
		List<Interest> results = dao.findInterestsForItem(id);
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> findById(@PathVariable String id) {
		Item result = dao.findById(id);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(params= {"tag"})
	public ResponseEntity<List<Item>> findActiveItemsByTag(@RequestParam(value = "tag") String tag) {
		List<Item> items = tagDao.getItemsFromTag(tag);
		return ResponseEntity.ok().body(items);
	}
	
	@PostMapping("/{id}/tags")
	public void addTagToItem(@PathVariable String id, @RequestBody tag tag) {
		tagDao.addTagToItem(id, tag.getTagName());
	}

	@GetMapping
	public ResponseEntity<List<Item>> findActiveItems() {
		List<Item> items = dao.findActive();
		return ResponseEntity.ok().body(items);
	}

	
}
