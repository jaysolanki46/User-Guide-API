package com.skyzer.server.main.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.skyzer.server.main.DBConfig;
import com.skyzer.server.main.bean.Division;

@Component 
public class divisionDAO {
	
	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	private List<Division> divisions;
	private Division division;
	private Integer deleteStatus;

	public List<Division> findAll() throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			st = cnn.createStatement();
			divisions = new ArrayList<Division>();
			
			rs = st.executeQuery("select * from divisions");
			
			while (rs.next()) {
				this.division = new Division(rs.getInt("id"), rs.getString("division"), rs.getString("dealer_name"));
				divisions.add(this.division);
			}

			return divisions;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public Division find(Integer divisionAccount) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from divisions where id = ?");
			ps.setInt(1, divisionAccount);
			rs = ps.executeQuery();
			
			if (rs.next()) 
				this.division = new Division(rs.getInt("id"), rs.getString("division"), rs.getString("dealer_name"));
			else
				this.division = null;
			
			return this.division;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public Division create(Division division) throws SQLException {
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("insert into divisions (division, dealer_name) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, division.getDivision());
			ps.setString(2, division.getDealer_name());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				this.division = new Division();
				this.division.setId(rs.getInt(1));
			}

			return this.division;
			
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
			
			ps = cnn.prepareStatement("delete from divisions where id = ?");
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
	
	public Division findByDivision(String division) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from divisions where division = ?");
			ps.setString(1, division);
			rs = ps.executeQuery();
			
			if (rs.next()) 
				this.division = new Division(rs.getInt("id"), rs.getString("division"), rs.getString("dealer_name"));
			else
				this.division = null;
				
			return this.division;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
}
