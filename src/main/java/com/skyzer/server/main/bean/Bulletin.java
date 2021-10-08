package com.skyzer.server.main.bean;

public class Bulletin {

	private Integer id;
	private Integer number;
	private String name;
	private String link;
	
	public Bulletin() {
	}
	
	public Bulletin(Integer id, Integer number, String name, String link) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.link = link;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Bulletin [id=" + id + ", number=" + number + ", name=" + name + ", link=" + link + "]";
	}
	
}
