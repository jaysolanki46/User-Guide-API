package com.skyzer.server.main.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	private Integer id;
	private String image;
	private String username;
	@JsonIgnore
	private String password;
	private Division division;
	
	public User() {
	}

	public Integer getId() {
		return id;
	}
	public User(Integer id, String image, String username, String password, Division division) {
		super();
		this.id = id;
		this.image = image;
		this.username = username;
		this.password = password;
		this.division = division;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", image=" + image + ", username=" + username + ", password=" + password
				+ ", division=" + division + "]";
	}
}
