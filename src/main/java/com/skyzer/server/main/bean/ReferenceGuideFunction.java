package com.skyzer.server.main.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReferenceGuideFunction {

	private Integer id;
	private String name;
	private String short_solution;
	private String long_solution;
	private String note;
	private Boolean is_telium;
	private Boolean is_tetra;
	private Boolean is_function;
	private Boolean is_menu;
	@JsonIgnore
	private User created_by;
	@JsonIgnore
	private String created_on;
	@JsonIgnore
	private User updated_by;
	@JsonIgnore
	private String updated_on;
	private Boolean is_favorite;
	
	public ReferenceGuideFunction() {
	}
	public ReferenceGuideFunction(Integer id, String name, String short_solution, String long_solution, String note,
			Boolean is_telium, Boolean is_tetra, Boolean is_function, Boolean is_menu, User created_by,
			String created_on, User updated_by, String updated_on, Boolean is_favorite) {
		super();
		this.id = id;
		this.name = name;
		this.short_solution = short_solution;
		this.long_solution = long_solution;
		this.note = note;
		this.is_telium = is_telium;
		this.is_tetra = is_tetra;
		this.is_function = is_function;
		this.is_menu = is_menu;
		this.created_by = created_by;
		this.created_on = created_on;
		this.updated_by = updated_by;
		this.updated_on = updated_on;
		this.is_favorite = is_favorite;
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
	public String getShort_solution() {
		return short_solution;
	}
	public void setShort_solution(String short_solution) {
		this.short_solution = short_solution;
	}
	public String getLong_solution() {
		return long_solution;
	}
	public void setLong_solution(String long_solution) {
		this.long_solution = long_solution;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Boolean getIs_telium() {
		return is_telium;
	}
	public void setIs_telium(Boolean is_telium) {
		this.is_telium = is_telium;
	}
	public Boolean getIs_tetra() {
		return is_tetra;
	}
	public void setIs_tetra(Boolean is_tetra) {
		this.is_tetra = is_tetra;
	}
	public Boolean getIs_function() {
		return is_function;
	}
	public void setIs_function(Boolean is_function) {
		this.is_function = is_function;
	}
	public Boolean getIs_menu() {
		return is_menu;
	}
	public void setIs_menu(Boolean is_menu) {
		this.is_menu = is_menu;
	}
	public User getCreated_by() {
		return created_by;
	}
	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public User getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(User updated_by) {
		this.updated_by = updated_by;
	}
	public String getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}
	public Boolean getIs_favorite() {
		return is_favorite;
	}
	public void setIs_favorite(Boolean is_favorite) {
		this.is_favorite = is_favorite;
	}
	@Override
	public String toString() {
		return "ReferenceGuideFunction [id=" + id + ", name=" + name + ", short_solution=" + short_solution
				+ ", long_solution=" + long_solution + ", note=" + note + ", is_telium=" + is_telium + ", is_tetra="
				+ is_tetra + ", is_function=" + is_function + ", is_menu=" + is_menu + ", created_by=" + created_by
				+ ", created_on=" + created_on + ", updated_by=" + updated_by + ", updated_on=" + updated_on
				+ ", is_favorite=" + is_favorite + "]";
	}
}
