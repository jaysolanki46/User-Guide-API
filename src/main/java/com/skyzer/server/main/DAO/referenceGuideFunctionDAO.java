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
	private referenceGuideCategoryDAO referenceGuideCategoryDAO;
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
				this.referenceGuideFunction.setReference_guide_category(referenceGuideCategoryDAO.find(rs.getInt("reference_guide_category")));
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
				this.referenceGuideFunction.setReference_guide_category(referenceGuideCategoryDAO.find(rs.getInt("reference_guide_category")));
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
			
			ps = cnn.prepareStatement("insert into reference_guide_functions (name, short_solution, long_solution, note, reference_guide_category, created_by, created_on, updated_by) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, referenceGuideFunction.getName());
			ps.setString(2, referenceGuideFunction.getShort_solution());
			ps.setString(3, referenceGuideFunction.getLong_solution());
			ps.setString(4, referenceGuideFunction.getNote());
			ps.setInt(5, referenceGuideFunction.getReference_guide_category().getId());
			ps.setInt(6, referenceGuideFunction.getCreated_by().getId());
			ps.setString(7, referenceGuideFunction.getCreated_on());
			ps.setInt(8, referenceGuideFunction.getUpdated_by().getId());
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
}
