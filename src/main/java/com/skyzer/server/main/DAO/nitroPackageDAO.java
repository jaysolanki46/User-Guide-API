package com.skyzer.server.main.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;
import com.skyzer.server.main.DBConfig;
import com.skyzer.server.main.Email;

@Component 
public class nitroPackageDAO {

	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	
	public Boolean find(String email) throws SQLException {
		
		try {
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from nitro_package");
			rs = ps.executeQuery();
			
			if (rs.next()) {
				/** SEND NITRO LINK EMAIL */
				return new Email().sendNitroLink(email, rs.getString("name"), rs.getString("link"), rs.getString("size"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		return false;
	}
}
