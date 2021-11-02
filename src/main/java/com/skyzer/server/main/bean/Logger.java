package com.skyzer.server.main.bean;

public class Logger {

	private String datetime;
	private String screen;
	private String module;
	private String user;
	private String status;
	
	public Logger(String datetime, String screen, String module, String user, String status) {
		super();
		this.datetime = datetime;
		this.screen = screen;
		this.module = module;
		this.user = user;
		this.status = status;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
