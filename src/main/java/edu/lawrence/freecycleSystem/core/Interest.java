package edu.lawrence.freecycleSystem.core;
public class Interest {
	private int interest_id;
	private String  item_id;
	private String interested_user;
	private String entered;
	
	public Interest() {}

	public int getInterestId() {
		return interest_id;
	}

	public void setInterestId(int interest_id) {
		this.interest_id = interest_id;
	}

	public String getItemId() {
		return item_id;
	}

	public void setItemId(String item_id) {
		this.item_id = item_id;
	}

	public String getInterestedUser() {
		return interested_user;
	}

	public void setInterestedUser(String interested_user) {
		this.interested_user = interested_user;
	}

	public String getEntered() {
		return entered;
	}

	public void setEntered(String entered) {
		this.entered = entered;
	}
}
