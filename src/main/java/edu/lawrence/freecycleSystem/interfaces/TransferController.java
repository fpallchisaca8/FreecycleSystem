package edu.lawrence.freecycleSystem.interfaces;

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

import edu.lawrence.freecycleSystem.core.TransferDAO;
import edu.lawrence.freecycleSystem.interfaces.dtos.OfferResponse;

@RestController
@RequestMapping("/transfers")
@CrossOrigin(origins = "*")
public class TransferController {
	private final TransferDAO dao;

    public TransferController(TransferDAO dao) {	
        this.dao = dao;
    }
	
    @GetMapping("/select-recipients/{itemId}")
    public String selectRecipients(@PathVariable String itemId, @RequestParam String recipientId) {
    	dao.saveSelection(itemId, recipientId);
    	return "Recipient selected.";
    }
    
	@PostMapping("/offerresponse")
	public ResponseEntity<String> offerresponse(@RequestBody OfferResponse or) {
		if(dao.saveScheduleResponse(or))
			return ResponseEntity.status(HttpStatus.CREATED).body("Offer accepted");
		else {
			dao.completeTransfer(or.getTransferId());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Offer cancelled");
		}
	}
}
