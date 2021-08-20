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
import com.skyzer.server.main.bean.UserFavorite;

@Component
public class userFavoriteDAO {

	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	private List<UserFavorite> userFavorites;
	private UserFavorite userFavorite;
	private Integer deleteStatus;
	
	@Autowired
	private userDAO userDAO;
	@Autowired
	private referenceGuideFunctionDAO referenceGuideFunctionDAO;
	
	public List<UserFavorite> findAll() throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			userFavorites = new ArrayList<UserFavorite>();
			
			rs = st.executeQuery("select * from user_favorites");
			
			while (rs.next()) {
				
				this.userFavorite = new UserFavorite();
				this.userFavorite.setId(rs.getInt("id"));
				this.userFavorite.setUser(userDAO.find(rs.getInt("user")));
				this.userFavorite.setFavorite_reference_guide_function(referenceGuideFunctionDAO.find(rs.getInt("favorite_reference_guide_function")));
				userFavorites.add(this.userFavorite);
			}

			return userFavorites;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public UserFavorite find(Integer id) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from user_favorites where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				this.userFavorite = new UserFavorite();
				this.userFavorite.setId(rs.getInt("id"));
				this.userFavorite.setUser(userDAO.find(rs.getInt("user")));
				this.userFavorite.setFavorite_reference_guide_function(referenceGuideFunctionDAO.find(rs.getInt("favorite_reference_guide_function")));
			}

			return this.userFavorite;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public UserFavorite create(UserFavorite userFavorite) throws SQLException {
		try {
			new DBConfig();
			cnn = DBConfig.connection();

			ps = cnn.prepareStatement("insert into user_favorites (user, favorite_reference_guide_function) "
					+ "values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userFavorite.getUser().getId());
			ps.setInt(2, userFavorite.getFavorite_reference_guide_function().getId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				this.userFavorite = new UserFavorite();
				this.userFavorite.setId(rs.getInt(1));
				this.userFavorite.setUser(userFavorite.getUser());
			}

			return this.userFavorite;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		return null;
	}
	
	public UserFavorite delete(UserFavorite userFavorite) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("delete from user_favorites where user = ? and favorite_reference_guide_function = ?");
			ps.setInt(1, userFavorite.getUser().getId());
			ps.setInt(2, userFavorite.getFavorite_reference_guide_function().getId());
			deleteStatus = ps.executeUpdate();
			
			this.userFavorite = new UserFavorite();
			this.userFavorite.setUser(userFavorite.getUser());
			
			return this.userFavorite;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		return null;
	}
}
