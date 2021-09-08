package com.skyzer.server.main.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.skyzer.server.main.DAO.userDAO;
import com.skyzer.server.main.bean.User;

@RestController
public class UserController {

	@Autowired
	private userDAO userDAO;
	
	@GetMapping("skyzer-guide/users")
	public ResponseEntity<List<User>> getAllUsers() {
		
		try {
			List<User> users = userDAO.findAll();
			if(users.isEmpty() || users == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		
		try {
			User user = userDAO.find(id);

			if(user == null) return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/users/user/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		
		try {
			User user = userDAO.findByEmail(email);

			if(user == null) return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@PostMapping("skyzer-guide/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	 	
	 	try {
	 		User newUser =	userDAO.create(user);
	 		
	 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		 	return ResponseEntity.created(location).build();
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/users/forgetPassword/{email}")
	public ResponseEntity<Object> forgetPassword(@PathVariable String email) {
		
		try {
			Boolean isValidUser = userDAO.findByEmailForForgetPassword(email);

			if(!isValidUser) return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>("", HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("skyzer-guide/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		try {
	 		boolean isDeleted =	userDAO.delete(id);
	 		
	 		if(!isDeleted) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			else return new ResponseEntity<>("", HttpStatus.OK); 
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
