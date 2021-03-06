package com.skyzer.server.main.bean;

public class Division {

	private Integer id;
	private String division;
	private String dealer_name;
	
	public Division() {
	}
	public Division(Integer id, String division, String dealer_name) {
		super();
		this.id = id;
		this.division = division;
		this.dealer_name = dealer_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}
	@Override
	public String toString() {
		return "Division [id=" + id + ", division=" + division + ", dealer_name=" + dealer_name + "]";
	}
}
