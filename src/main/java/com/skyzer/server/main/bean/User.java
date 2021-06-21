package com.skyzer.server.main.bean;

public class User {

	private Integer id;
	private String username;
	private String password;
	private Division division;
	
	public User() {
	}
	public User(Integer id, String username, String password, Division division) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.division = division;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", division=" + division + "]";
	}
}
