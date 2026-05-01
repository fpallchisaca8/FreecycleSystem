package edu.lawrence.freecycleSystem.interfaces.dtos;

import edu.lawrence.freecycleSystem.core.Transfer;

public class Donation {
	private String transfer_id;
	private String item_id;
	private String item_title;
	private String site_name;
	private String time_slot;

	public Donation() {
	}

	public Donation(Transfer base) {
		this.transfer_id = base.getTransferId();
		this.item_id = base.getItemId();
		this.item_title = base.getItemTitle();
		this.site_name = base.getSiteName();
		this.time_slot = base.getTimeSlot();
	}

	public Donation(String transfer_id, String item_id, String item_title) {
    this.transfer_id = transfer_id;
    this.item_id = item_id;
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

	public String getSiteName() {
		return site_name;
	}

	public void setSiteName(String site_name) {
		this.site_name = site_name;
	}

	public String getTimeSlot() {
		return time_slot;
	}

	public void setTimeSlot(String time_slot) {
		this.time_slot = time_slot;
	}
}