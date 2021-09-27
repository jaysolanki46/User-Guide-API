package com.skyzer.server.main.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Team_Credits {
	
	private Integer id;
	private String image_name;
	private String title;
	private String full_name;
	private String linked_in;
	@JsonIgnore
	private String created_on;
	@JsonIgnore
	private User created_by;
	
	public Team_Credits(Integer id, String image_name, String title, String full_name, String linked_in, String gender,
			String created_on, User created_by) {
		super();
		this.id = id;
		this.image_name = image_name;
		this.title = title;
		this.full_name = full_name;
		this.linked_in = linked_in;
		this.created_on = created_on;
		this.created_by = created_by;
	}

	public Team_Credits() {
	}
	
	public String getImage_name() {
		return image_name;
	}


	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getLinked_in() {
		return linked_in;
	}

	public void setLinked_in(String linked_in) {
		this.linked_in = linked_in;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

}
