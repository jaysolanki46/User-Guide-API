package com.skyzer.server.main.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skyzer.server.main.DBConfig;
import com.skyzer.server.main.bean.User;

@Component
public class userDAO {

	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	private List<User> users;
	private User user;
	private Integer deleteStatus;
	
	@Autowired
	private divisionDAO divisionDAO;
	
	public List<User> findAll() throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			users = new ArrayList<User>();
			
			rs = st.executeQuery("select * from users");
			
			while (rs.next()) {
				
				this.user = new User();
				this.user.setId(rs.getInt("id"));
				this.user.setUsername(rs.getString("username"));
				this.user.setPassword("***");
				this.user.setDivision(divisionDAO.find(rs.getInt("division")));
				users.add(this.user);
			}

			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public User find(Integer id) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				this.user = new User();
				this.user.setId(rs.getInt("id"));
				this.user.setUsername(rs.getString("username"));
				this.user.setPassword("***");
				this.user.setDivision(divisionDAO.find(rs.getInt("division")));
			}

			return this.user;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public User create(User user) throws SQLException {
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("insert into users (username, pass, division) "
					+ "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, divisionDAO.findByDivision(user.getDivision().getDivision()).getId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				this.user = new User();
				this.user.setId(rs.getInt(1));
			}

			return this.user;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		return null;
	}
	
	public boolean delete(Integer id) throws SQLException {
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("delete from users where id = ?");
			ps.setInt(1, id);
			deleteStatus = ps.executeUpdate();
			
			if(deleteStatus == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		return false;
	}
}
