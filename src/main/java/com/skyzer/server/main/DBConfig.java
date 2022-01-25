package com.skyzer.server.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	public static Connection connection() throws ClassNotFoundException, SQLException {
		 
		// For production
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		
		// For test
		//Class.forName("com.mysql.cj.jdbc.Driver");
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/skyzerguide?serverTimezone=UTC&useSSL=false", "root", "root");

		return connection;
		 
	}
	
}
