package edu.lawrence.freecycleSystem.core;

public class Transfer {
	private String transfer_id;
	private String item_id;
	private String item_title;
	private String doner_id;
	private String recipient_id;
	private String status;
	private String site_name;
	private String time_slot;

	public Transfer() {}

	public String getTransferId() {
		return transfer_id;
	}

	public void setTransferId(String transferId) {
		this.transfer_id = transferId;
	}

	public String getItemId() {
		return item_id;
	}

	public void setItemId(String itemId) {
		this.item_id = itemId;
	}

	public String getItemTitle() {
		return item_title;
	}

	public void setItemTitle(String item_title) {
		this.item_title = item_title;
	}

	public String getDonerId() {
		return doner_id;
	}

	public void setDonerId(String doner) {
		this.doner_id = doner;
	}

	public String getRecipientId() {
		return recipient_id;
	}

	public void setRecipientId(String recipient) {
		this.recipient_id = recipient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    public int getInterestId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
