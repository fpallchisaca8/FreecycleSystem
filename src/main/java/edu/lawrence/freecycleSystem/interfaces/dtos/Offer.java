package edu.lawrence.freecycleSystem.interfaces.dtos;

import edu.lawrence.freecycleSystem.core.Transfer;

public class Offer {
	private String transfer_id;
	private String item_id;
	private String item_title;

	public Offer() {}

	public Offer(Transfer base, String item_title) {
		transfer_id = base.getTransferId();
		item_id = base.getItemId();
		this.item_title = item_title;
	}
	
	public String getTransferId() {
		return transfer_id;
	}

	public void setTransferId(String transfer_id) {
		this.transfer_id = transfer_id;
	}

	public String getItemId() {
		return item_id;
	}

	public void setItemId(String item_id) {
		this.item_id = item_id;
	}

	public String getItemTitle() {
		return item_title;
	}

	public void setItemTitle(String item_title) {
		this.item_title = item_title;
	}
}
