package com.skyzer.server.main.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skyzer.server.main.DBConfig;
import com.skyzer.server.main.bean.Bulletin;

@Component 
public class bulletinDAO {

	private Connection cnn;
	private ResultSet rs;
	private Statement st;
	
	private List<Bulletin> bulletins;
	private Bulletin bulletin;
	
	public List<Bulletin> findAll() throws SQLException {
		
		try {
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			bulletins = new ArrayList<Bulletin>();
			
			rs = st.executeQuery("select * from bulletins order by id desc");
			
			while (rs.next()) {
				this.bulletin = new Bulletin();
				this.bulletin.setId(rs.getInt("id"));
				this.bulletin.setNumber(rs.getInt("number"));
				this.bulletin.setName(rs.getString("name"));
				this.bulletin.setLink(rs.getString("link"));
				
				bulletins.add(this.bulletin);
			}

			return bulletins;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
}
