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
import com.skyzer.server.main.bean.ReferenceGuideFunction;

@Component
public class referenceGuideFunctionDAO {

	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	private List<ReferenceGuideFunction> referenceGuideFunctions;
	private ReferenceGuideFunction referenceGuideFunction;
	private Integer deleteStatus;
	
	@Autowired
	private userDAO userDAO;
	
	public List<ReferenceGuideFunction> findAll() throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			referenceGuideFunctions = new ArrayList<ReferenceGuideFunction>();
			
			rs = st.executeQuery("select * from reference_guide_functions");
			
			while (rs.next()) {
				this.referenceGuideFunction = new ReferenceGuideFunction();
				this.referenceGuideFunction.setId(rs.getInt("id"));
				this.referenceGuideFunction.setName(rs.getString("name"));
				this.referenceGuideFunction.setShort_solution(rs.getString("short_solution"));
				this.referenceGuideFunction.setLong_solution(rs.getString("long_solution"));
				this.referenceGuideFunction.setNote(rs.getString("note"));
				this.referenceGuideFunction.setIs_telium(rs.getBoolean("is_telium"));
				this.referenceGuideFunction.setIs_tetra(rs.getBoolean("is_tetra"));
				this.referenceGuideFunction.setIs_function(rs.getBoolean("is_function"));
				this.referenceGuideFunction.setIs_menu(rs.getBoolean("is_menu"));
				this.referenceGuideFunction.setCreated_by(userDAO.find(rs.getInt("created_by")));
				this.referenceGuideFunction.setCreated_on(rs.getString("created_on"));
				this.referenceGuideFunction.setUpdated_by(userDAO.find(rs.getInt("updated_by")));
				this.referenceGuideFunction.setUpdated_on(rs.getString("updated_on"));
				
				referenceGuideFunctions.add(this.referenceGuideFunction);
			}

			return referenceGuideFunctions;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public ReferenceGuideFunction find(Integer id) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from reference_guide_functions where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				this.referenceGuideFunction = new ReferenceGuideFunction();
				this.referenceGuideFunction.setId(rs.getInt("id"));
				this.referenceGuideFunction.setName(rs.getString("name"));
				this.referenceGuideFunction.setShort_solution("short_solution");
				this.referenceGuideFunction.setLong_solution("long_solution");
				this.referenceGuideFunction.setNote("note");
				this.referenceGuideFunction.setIs_telium(rs.getBoolean("is_telium"));
				this.referenceGuideFunction.setIs_tetra(rs.getBoolean("is_tetra"));
				this.referenceGuideFunction.setIs_function(rs.getBoolean("is_function"));
				this.referenceGuideFunction.setIs_menu(rs.getBoolean("is_menu"));
				this.referenceGuideFunction.setCreated_by(userDAO.find(rs.getInt("created_by")));
				this.referenceGuideFunction.setCreated_on(rs.getString("created_on"));
				this.referenceGuideFunction.setUpdated_by(userDAO.find(rs.getInt("updated_by")));
				this.referenceGuideFunction.setUpdated_on(rs.getString("updated_on"));
			}

			return this.referenceGuideFunction;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public ReferenceGuideFunction create(ReferenceGuideFunction referenceGuideFunction) throws SQLException {
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("insert into reference_guide_functions (name, short_solution, long_solution, note, is_telium, is_tetra, is_function, is_menu, created_by, created_on, updated_by) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, referenceGuideFunction.getName());
			ps.setString(2, referenceGuideFunction.getShort_solution());
			ps.setString(3, referenceGuideFunction.getLong_solution());
			ps.setString(4, referenceGuideFunction.getNote());
			ps.setBoolean(5,  referenceGuideFunction.getIs_telium());
			ps.setBoolean(6,  referenceGuideFunction.getIs_tetra());
			ps.setBoolean(7,  referenceGuideFunction.getIs_function());
			ps.setBoolean(8,  referenceGuideFunction.getIs_menu());
			ps.setInt(9, referenceGuideFunction.getCreated_by().getId());
			ps.setString(10, referenceGuideFunction.getCreated_on());
			ps.setInt(11, referenceGuideFunction.getUpdated_by().getId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				this.referenceGuideFunction = new ReferenceGuideFunction();
				this.referenceGuideFunction.setId(rs.getInt(1));
			}

			return this.referenceGuideFunction;
			
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
			
			ps = cnn.prepareStatement("delete from reference_guide_functions where id = ?");
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

	public List<ReferenceGuideFunction> findAllByUser(Integer userId) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			referenceGuideFunctions = new ArrayList<ReferenceGuideFunction>();
			
			ps = cnn.prepareStatement("SELECT reference_guide_functions.*, "
										+ "CASE WHEN user_favorites.id IS NULL THEN 0 ELSE 1 END AS is_favourite "
										+ "FROM reference_guide_functions LEFT JOIN user_favorites "
										+ "on reference_guide_functions.id = user_favorites.favorite_reference_guide_function "
										+ "and user_favorites.user = ? group by reference_guide_functions.id");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				this.referenceGuideFunction = new ReferenceGuideFunction();
				this.referenceGuideFunction.setId(rs.getInt("id"));
				this.referenceGuideFunction.setName(rs.getString("name"));
				this.referenceGuideFunction.setShort_solution(rs.getString("short_solution"));
				this.referenceGuideFunction.setLong_solution(rs.getString("long_solution"));
				this.referenceGuideFunction.setNote(rs.getString("note"));
				this.referenceGuideFunction.setIs_telium(rs.getBoolean("is_telium"));
				this.referenceGuideFunction.setIs_tetra(rs.getBoolean("is_tetra"));
				this.referenceGuideFunction.setIs_function(rs.getBoolean("is_function"));
				this.referenceGuideFunction.setIs_menu(rs.getBoolean("is_menu"));
				this.referenceGuideFunction.setCreated_by(userDAO.find(rs.getInt("created_by")));
				this.referenceGuideFunction.setCreated_on(rs.getString("created_on"));
				this.referenceGuideFunction.setUpdated_by(userDAO.find(rs.getInt("updated_by")));
				this.referenceGuideFunction.setUpdated_on(rs.getString("updated_on"));
				this.referenceGuideFunction.setIs_favorite(rs.getBoolean("is_favourite"));
				
				referenceGuideFunctions.add(this.referenceGuideFunction);
			}

			return referenceGuideFunctions;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
}
