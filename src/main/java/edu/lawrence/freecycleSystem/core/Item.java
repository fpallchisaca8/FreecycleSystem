package edu.lawrence.freecycleSystem.core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	private String item_id;
	@JsonProperty("donor_id")
	private String donor_id;
	private String title;
	private String description;
	private String imageurl;
	private List<String> tags;
	private String status;
	
	public Item() {
		this.tags = new ArrayList<>();
	}

	public String getItemId() {
		return item_id;
	}

	public void setItemId(String item_id) {
		this.item_id = item_id;
	}

	public String getDonorId() {
		return donor_id;
	}

	public void setDonorId(String donor_id) {
		this.donor_id = donor_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
