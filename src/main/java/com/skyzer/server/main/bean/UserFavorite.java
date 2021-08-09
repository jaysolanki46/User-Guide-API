package com.skyzer.server.main.bean;

public class UserFavorite {

	private Integer id;
	private User user;
	private ReferenceGuideFunction favorite_reference_guide_function;
	
	public UserFavorite() {}
	public UserFavorite(Integer id, User user, ReferenceGuideFunction favorite_reference_guide_function) {
		super();
		this.id = id;
		this.user = user;
		this.favorite_reference_guide_function = favorite_reference_guide_function;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ReferenceGuideFunction getFavorite_reference_guide_function() {
		return favorite_reference_guide_function;
	}
	public void setFavorite_reference_guide_function(ReferenceGuideFunction favorite_reference_guide_function) {
		this.favorite_reference_guide_function = favorite_reference_guide_function;
	}
	@Override
	public String toString() {
		return "UserFavorite [id=" + id + ", user=" + user + ", favorite_reference_guide_function="
				+ favorite_reference_guide_function + "]";
	}
}
