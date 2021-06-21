package com.skyzer.server.main.bean;

public class Division {

	private Integer id;
	private Integer division;
	private Integer dealer_name;
	
	public Division() {
	}
	public Division(Integer id, Integer division, Integer dealer_name) {
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
	public Integer getDivision() {
		return division;
	}
	public void setDivision(Integer division) {
		this.division = division;
	}
	public Integer getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(Integer dealer_name) {
		this.dealer_name = dealer_name;
	}
	@Override
	public String toString() {
		return "Division [id=" + id + ", division=" + division + ", dealer_name=" + dealer_name + "]";
	}
}
