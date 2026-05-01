package edu.lawrence.freecycleSystem.interfaces.dtos;

import edu.lawrence.freecycleSystem.core.Transfer;

public class Receipt {
	String transfer_id;
	String item_title;
	String donor_name;
	String site_name;
	String time_slot;

	public Receipt() {
	}

	public Receipt(Transfer base, String item_title, String donor_name, String site_name, String time_slot) {
		transfer_id = base.getTransferId();
		this.item_title = item_title;
		this.donor_name = donor_name;
		this.site_name = site_name;
		this.time_slot = time_slot;
	}

	public String getTransferId(){
		return transfer_id;
	}

	public void setTransferId(String transfer_id) {
		this.transfer_id = transfer_id;
	}

	public String getItemTitle() {
		return item_title;
	}

	public void setItemTitle(String item_title) {
		this.item_title = item_title;
	}

	public String getDonerName() {
		return donor_name;
	}

	public void setDonerName(String donor_name) {
		this.donor_name = donor_name;
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
