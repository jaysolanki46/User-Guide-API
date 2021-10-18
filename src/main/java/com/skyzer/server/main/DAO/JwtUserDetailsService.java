package com.skyzer.server.main.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skyzer.server.main.bean.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private userDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user;
		try {
			user = userDAO.getUserByEmail(username);
			if(user != null) {
				return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
						new ArrayList<>());
			}else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
