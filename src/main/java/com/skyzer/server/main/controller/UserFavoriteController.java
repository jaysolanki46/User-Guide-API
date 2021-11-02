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
	
	@PostMapping("/userFavorites/tetra/user/")
	public ResponseEntity<List<ReferenceGuideFunction>> createUserFavoriteFromTetraList(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite newUserFavorite =	userFavoriteDAO.create(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllTetraByUser(newUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("/userFavorites/tetra/user/")
	public ResponseEntity<List<ReferenceGuideFunction>> deleteUserFavoriteFromTetraList(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite deletedUserFavorite =	userFavoriteDAO.delete(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllTetraByUser(deletedUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@PostMapping("/userFavorites/telium/user/")
	public ResponseEntity<List<ReferenceGuideFunction>> createUserFavoriteFromTeliumList(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite newUserFavorite =	userFavoriteDAO.create(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllTeliumByUser(newUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("/userFavorites/telium/user/")
	public ResponseEntity<List<ReferenceGuideFunction>> deleteUserFavoriteFromTeliumList(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite deletedUserFavorite =	userFavoriteDAO.delete(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllTeliumByUser(deletedUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}

	@GetMapping("/userFavorites/user/{id}")
	public ResponseEntity<List<ReferenceGuideFunction>> getOnlyFavouritesByUser(@PathVariable Integer id) {
	 	
	 	try {
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctionsByUserFavourites = 
					referenceGuideFunctionDAO.findAllFavoritesByUser(id);
	 		
			if(referenceGuideFunctionsByUserFavourites.isEmpty() || referenceGuideFunctionsByUserFavourites == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctionsByUserFavourites, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("/userFavorites/user/")
	public ResponseEntity<List<ReferenceGuideFunction>> deleteUserFavorite(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		
	 		UserFavorite deletedUserFavorite =	userFavoriteDAO.delete(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctionsByUserFavourites = 
					referenceGuideFunctionDAO.findAllFavoritesByUser(deletedUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctionsByUserFavourites.isEmpty() || referenceGuideFunctionsByUserFavourites == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctionsByUserFavourites, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	
	
	/*
	@PostMapping("/userFavorites/favorites")
	public ResponseEntity<List<ReferenceGuideFunction>> createUserFavoriteFromFavoriteList(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite newUserFavorite =	userFavoriteDAO.create(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllFavoritesByUser(newUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("/userFavorites/favorites")
	public ResponseEntity<List<ReferenceGuideFunction>> deleteUserFavoriteFromFavoriteList(@RequestBody UserFavorite userFavorite) {
	 	
	 	try {
	 		UserFavorite deletedUserFavorite =	userFavoriteDAO.delete(userFavorite);
	 		
	 		List<ReferenceGuideFunction> referenceGuideFunctions = 
					referenceGuideFunctionDAO.findAllFavoritesByUser(deletedUserFavorite.getUser().getId());
	 		
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}*/
}
