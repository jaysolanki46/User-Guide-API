package com.skyzer.server.main.bean;

public class NitroPackage {

	private Integer id;
	private String name;
	private String link;
	private String size;
	
	public NitroPackage() {
	}
	
	public NitroPackage(Integer id, String name, String link, String size) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.size = size;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "NitroPackage [id=" + id + ", name=" + name + ", link=" + link + ", size=" + size + "]";
	}

}
