package com.skyzer.server.main.DAO;

import java.sql.SQLException;
import org.springframework.stereotype.Component;
import com.skyzer.server.main.bean.Logger;

@Component 
public class LoggerDAO {

	public Boolean create(Logger logger) throws SQLException {
		try {
			System.err.println(logger.getDatetime() + " " + logger.getScreen() );
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return false;
	}
}
