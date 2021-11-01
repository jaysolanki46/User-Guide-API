package com.skyzer.server.main.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.mail.Transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.skyzer.server.main.DBConfig;
import com.skyzer.server.main.Email;
import com.skyzer.server.main.bean.User;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

@Component
public class userDAO {

	private Connection cnn;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;
	
	private List<User> users;
	private User user;
	private Integer deleteStatus;
	private Email  email;
	private PasswordEncoder passwordEncoder;
	private String encodedPassword;
	
	private DateTimeFormatter dtf;
	private LocalDateTime now;
	
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
				this.user.setImage(rs.getString("image"));
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
				this.user.setImage(rs.getString("image"));
				this.user.setUsername(rs.getString("username"));
				this.user.setEmail(rs.getString("email"));
				this.user.setPassword(rs.getString("pass"));
				//this.user.setPassword("***");
				this.user.setDivision(divisionDAO.find(rs.getInt("division")));
				this.user.setCreated_on(rs.getString("created_on"));
				this.user.setUpdated_on(rs.getString("updated_on"));
				this.user.setIs_active(rs.getBoolean("is_active"));
			}

			return this.user;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public User findByEmail(String email) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from users where email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				this.user = new User();
				this.user.setId(rs.getInt("id"));
				this.user.setEmail(rs.getString("email"));
				this.user.setUsername(rs.getString("username"));
				this.user.setImage(rs.getString("image"));
			} else {
				this.user = null;
			}

			return this.user;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		
		return null;
	}
	
	public Boolean verifyUserAndSendCodeOnForgetPassword(String emailId) throws SQLException {
		
		try {
			dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			now = LocalDateTime.now();  
			
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from users where email = ? and is_active = ?");
			ps.setString(1, emailId);
			ps.setBoolean(2, true);
			rs = ps.executeQuery();
			
			/** GENERATE RANDOM CODE */
			Random rnd = new Random();
			Integer code = rnd.nextInt(9999);
			
			if (rs.next()) {
				
				/** INSERT CODE IN USER ROW  */
				ps = cnn.prepareStatement("Update users set forget_code = ?, updated_on = ? where id = ?");
				ps.setString(1, code.toString());
				ps.setString(2, dtf.format(now));
				ps.setInt(3, rs.getInt("id"));
				ps.executeUpdate();
				
				/** SENDING CODE THROUGH EMAIL */
				email = new Email();
				email.sendForgotPasswordDetailsToUser(rs.getString("email"), rs.getString("username"), code.toString());

				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			cnn.close();
		}
	}
	
	public Boolean verifyUserEmailAndCode(User user) throws SQLException {
		
		try {
			cnn = DBConfig.connection();
			dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			now = LocalDateTime.now(); 
			
			ps = cnn.prepareStatement("select * from users where email = ? and forget_code = ? and is_active = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getForget_code());
			ps.setBoolean(3, true);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				/** RESET CODE IN USER ROW  */
				ps = cnn.prepareStatement("Update users set forget_code = ?, updated_on = ? where id = ?");
				ps.setString(1, null);
				ps.setString(2, dtf.format(now));
				ps.setInt(3, rs.getInt("id"));
				ps.executeUpdate();
				
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			cnn.close();
		}
	}
	
	public Boolean resetPassword(User user) throws SQLException {
		
		try {
			cnn = DBConfig.connection();
			dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			now = LocalDateTime.now(); 
			this.passwordEncoder = new BCryptPasswordEncoder();
			this.encodedPassword = this.passwordEncoder.encode(user.getPassword());
			
			ps = cnn.prepareStatement("Update users set pass = ?, updated_on = ? where email = ?");
			ps.setString(1, this.encodedPassword);
			ps.setString(2, dtf.format(now));
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			cnn.close();
		}
	}
	
	public User create(User user) throws SQLException {
		try {
			cnn = DBConfig.connection();
			this.passwordEncoder = new BCryptPasswordEncoder();
			this.encodedPassword = this.passwordEncoder.encode(user.getPassword());
			
			ps = cnn.prepareStatement("insert into users (image, username, email, pass, division) "
					+ "values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getImage());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, this.encodedPassword);
			ps.setInt(5, divisionDAO.findByDivision(user.getDivision().getDivision()).getId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				this.user = new User();
				this.user.setId(rs.getInt(1));
				
				
		           ExecutorService emailExecutor = Executors.newCachedThreadPool();
		           emailExecutor.execute(new Runnable() {
		               @Override
		               public void run() {
		                   try {
								email = new Email();
								/** SEND ACKLNOWLEDGEMENT */
								email.sendSignUpAcknowledgement(user.getEmail(), user.getUsername());

								TimeUnit.SECONDS.sleep(5);

								email = new Email();
								/** SEND EMAIL FOR ACTIVE THIS USER TO SUPPORT */
								email.sendToSupportToActiveUser(user);
		                   } catch (Exception e) {
		                	   e.printStackTrace();
		                   }
		               }
		           });
				
				System.err.println("Sent");
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
	
	public User getUserByEmail(String email) throws SQLException {
		
		try {
			new DBConfig();
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("select * from users where email = ? and is_active = ?");
			ps.setString(1, email);
			ps.setBoolean(2, true);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				this.user = new User();
				this.user.setEmail(rs.getString("email"));
				this.user.setPassword(rs.getString("pass"));
			} else {
				this.user = null;
			}
			
			return this.user;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cnn.close();
		}
		return user;
	}
	
	public Boolean upload(User user) throws SQLException {
		
		try {
			dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			now = LocalDateTime.now(); 
			
			cnn = DBConfig.connection();
			
			ps = cnn.prepareStatement("Update users set image = ?, updated_on = ? where id = ?");
			ps.setString(1, user.getImage());
			ps.setString(2, dtf.format(now));
			ps.setInt(3, user.getId());
			
			
			if (ps.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			cnn.close();
		}
	}
	
}
