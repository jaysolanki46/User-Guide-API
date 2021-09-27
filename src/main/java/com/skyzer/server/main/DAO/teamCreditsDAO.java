package com.skyzer.server.main.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skyzer.server.main.DBConfig;
import com.skyzer.server.main.bean.Team;

@Component
public class teamCreditsDAO {
	
	private Connection cnn;
	private ResultSet rs;
	private Statement st;
	
	private List<Team> teamList;
	private Team team;
	
	public List<Team> findAll() throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			teamList = new ArrayList<Team>();
			
			rs = st.executeQuery("select * from team_credits");
			
			while (rs.next()) {
				
				this.team = new Team();
				this.team.setId(rs.getInt("id"));
				this.team.setImage_name(rs.getString("image_name"));
				this.team.setTitle(rs.getString("title"));
				this.team.setFull_name(rs.getString("full_name"));
				this.team.setLinked_in(rs.getString("linked_in"));
				teamList.add(this.team);
			}

			return teamList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
}
