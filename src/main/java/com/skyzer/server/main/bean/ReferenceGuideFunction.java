package com.skyzer.server.main.bean;

public class ReferenceGuideFunction {

	private Integer id;
	private String name;
	private String short_solution;
	private String long_solution;
	private String note;
	private String reference_guide_category;
	private User created_by;
	private String created_on;
	private User updated_by;
	private String updated_on;
	
	public ReferenceGuideFunction() {
	}
	public ReferenceGuideFunction(Integer id, String name, String short_solution, String long_solution, String note,
			String reference_guide_category, User created_by, String created_on, User updated_by, String updated_on) {
		super();
		this.id = id;
		this.name = name;
		this.short_solution = short_solution;
		this.long_solution = long_solution;
		this.note = note;
		this.reference_guide_category = reference_guide_category;
		this.created_by = created_by;
		this.created_on = created_on;
		this.updated_by = updated_by;
		this.updated_on = updated_on;
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
	public String getReference_guide_category() {
		return reference_guide_category;
	}
	public void setReference_guide_category(String reference_guide_category) {
		this.reference_guide_category = reference_guide_category;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Reference_Guide_Function [id=" + id + ", name=" + name + ", short_solution=" + short_solution
				+ ", long_solution=" + long_solution + ", note=" + note + ", reference_guide_category="
				+ reference_guide_category + ", created_by=" + created_by + ", created_on=" + created_on
				+ ", updated_by=" + updated_by + ", updated_on=" + updated_on + "]";
	}
}
