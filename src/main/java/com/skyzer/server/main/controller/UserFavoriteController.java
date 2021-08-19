package com.skyzer.server.main.controller;

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
import com.skyzer.server.main.DAO.referenceGuideFunctionDAO;
import com.skyzer.server.main.DAO.userFavoriteDAO;
import com.skyzer.server.main.bean.ReferenceGuideFunction;
import com.skyzer.server.main.bean.UserFavorite;

@RestController
public class UserFavoriteController {

	@Autowired
	private userFavoriteDAO userFavoriteDAO;
	
	@Autowired
	private referenceGuideFunctionDAO referenceGuideFunctionDAO;
	
	@GetMapping("skyzer-guide/userFavorites")
	public ResponseEntity<List<UserFavorite>> getAllUserFavorites() {
		
		try {
			List<UserFavorite> userFavorites = userFavoriteDAO.findAll();
			if(userFavorites.isEmpty() || userFavorites == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(userFavorites, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/userFavorites/{id}")
	public ResponseEntity<UserFavorite> getUserFavorite(@PathVariable Integer id) {
		
		try {
			UserFavorite userFavorite = userFavoriteDAO.find(id);

			if(userFavorite == null) return new ResponseEntity<>(userFavorite, HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>(userFavorite, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@PostMapping("skyzer-guide/userFavorites")
	public ResponseEntity<List<ReferenceGuideFunction>> createUserFavorite(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite newUserFavorite =	userFavoriteDAO.create(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllByUser(newUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("skyzer-guide/userFavorites/{id}")
	public ResponseEntity<Object> deleteUserFavorite(@PathVariable Integer id) {
		try {
	 		boolean isDeleted =	userFavoriteDAO.delete(id);
	 		
	 		if(!isDeleted) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			else return new ResponseEntity<>("", HttpStatus.OK); 
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
