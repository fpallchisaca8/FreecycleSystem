package edu.lawrence.freecycleSystem.interfaces.dtos;

public class OfferResponse {
	private String transfer_id;
	private String user_id;
	private boolean accept;
	private String site_id;
	private String time_id;
	
	public OfferResponse() {}

	public String getTransferId() {
		return transfer_id;
	}

	public void setTransferId(String transfer_id) {
		this.transfer_id = transfer_id;
	}

	public String getUserId() {
		return user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public boolean getAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public String getSiteId() {
		return site_id;
	}

	public void setSiteId(String site_id) {
		this.site_id = site_id;
	}

	public String getTimeId() {
		return time_id;
	}

	public void setTimeId(String time_id) {
		this.time_id = time_id;
	}
}

