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
import com.skyzer.server.main.bean.ReferenceGuideCategory;

@Component
public class referenceGuideCategoryDAO {

	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	private List<ReferenceGuideCategory> referenceGuideCategories;
	private ReferenceGuideCategory referenceGuideCategory;
	private Integer deleteStatus;
	
	@Autowired
	private userDAO userDAO;
	
	public List<ReferenceGuideCategory> findAll() throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			referenceGuideCategories = new ArrayList<ReferenceGuideCategory>();
			
			rs = st.executeQuery("select * from reference_guide_categories");
			
			while (rs.next()) {
				
				this.referenceGuideCategory = new ReferenceGuideCategory();
				this.referenceGuideCategory.setId(rs.getInt("id"));
				this.referenceGuideCategory.setName(rs.getString("name"));
				this.referenceGuideCategory.setCreated_by(userDAO.find(rs.getInt("created_by")));
				this.referenceGuideCategory.setCreated_on(rs.getString("created_on"));
				this.referenceGuideCategory.setUpdated_by(userDAO.find(rs.getInt("updated_by")));
				this.referenceGuideCategory.setUpdated_on(rs.getString("updated_on"));
				referenceGuideCategories.add(this.referenceGuideCategory);
			}

			return referenceGuideCategories;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public ReferenceGuideCategory find(Integer id) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from reference_guide_categories where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				this.referenceGuideCategory = new ReferenceGuideCategory();
				this.referenceGuideCategory.setId(rs.getInt("id"));
				this.referenceGuideCategory.setName(rs.getString("name"));
				this.referenceGuideCategory.setCreated_by(userDAO.find(rs.getInt("created_by")));
				this.referenceGuideCategory.setCreated_on(rs.getString("created_on"));
				this.referenceGuideCategory.setUpdated_by(userDAO.find(rs.getInt("updated_by")));
				this.referenceGuideCategory.setUpdated_on(rs.getString("updated_on"));
			}

			return this.referenceGuideCategory;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public ReferenceGuideCategory create(ReferenceGuideCategory referenceGuideCategory) throws SQLException {
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("insert into reference_guide_categories (name, created_by, created_on, updated_by) "
					+ "values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, referenceGuideCategory.getName());
			ps.setInt(2, referenceGuideCategory.getCreated_by().getId());
			ps.setString(3, referenceGuideCategory.getCreated_on());
			ps.setInt(4, referenceGuideCategory.getUpdated_by().getId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				this.referenceGuideCategory = new ReferenceGuideCategory();
				this.referenceGuideCategory.setId(rs.getInt(1));
			}

			return this.referenceGuideCategory;
			
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
			
			ps = cnn.prepareStatement("delete from reference_guide_categories where id = ?");
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
