package com.skyzer.server.main.bean;

public class User {

	private Integer id;
	private String image;
	private String username;
	private String email;
	private String password;
	private Division division;
	private String created_on;
	private String updated_on;
	private Boolean is_active;
	
	public User() {
	}

	public User(Integer id, String image, String username, String email, String password, Division division,
			String created_on, String updated_on, Boolean is_active) {
		super();
		this.id = id;
		this.image = image;
		this.username = username;
		this.email = email;
		this.password = password;
		this.division = division;
		this.created_on = created_on;
		this.updated_on = updated_on;
		this.is_active = is_active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", image=" + image + ", username=" + username + ", email=" + email + ", password="
				+ password + ", division=" + division + ", created_on=" + created_on + ", updated_on=" + updated_on
				+ ", is_active=" + is_active + "]";
	}
	
}
