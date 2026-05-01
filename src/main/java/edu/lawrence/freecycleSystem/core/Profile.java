package edu.lawrence.freecycleSystem.core;

public class Profile {
	private String user;
	private String fullname;
	private String email;
	private String phone;
	private String general_location;
	private String bio;
	private boolean ispublic;
	
	public Profile() {}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGeneral_location() {
		return general_location;
	}

	public void setGeneral_location(String general_location) {
		this.general_location = general_location;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public boolean getIspublic() {
		return ispublic;
	}

	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
}
