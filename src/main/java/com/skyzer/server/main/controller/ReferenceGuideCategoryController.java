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
import com.skyzer.server.main.DAO.referenceGuideCategoryDAO;
import com.skyzer.server.main.bean.ReferenceGuideCategory;

@RestController
public class ReferenceGuideCategoryController {

	@Autowired
	private referenceGuideCategoryDAO referenceGuideCategoryDAO;
	
	@GetMapping("skyzer-guide/referenceGuideCategories")
	public ResponseEntity<List<ReferenceGuideCategory>> getAllReferenceGuideCategories() {
		
		try {
			List<ReferenceGuideCategory> referenceGuideCategories = referenceGuideCategoryDAO.findAll();
			if(referenceGuideCategories.isEmpty() || referenceGuideCategories == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideCategories, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/referenceGuideCategories/{id}")
	public ResponseEntity<ReferenceGuideCategory> getReferenceGuideCategory(@PathVariable Integer id) {
		
		try {
			ReferenceGuideCategory referenceGuideCategory = referenceGuideCategoryDAO.find(id);

			if(referenceGuideCategory == null) return new ResponseEntity<>(referenceGuideCategory, HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>(referenceGuideCategory, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	/** POST PATTERN FROM POSTMAN
		 {
		    "name": "NITRO FUNCTIONS",
		    "created_by": {
		        "id": 1
		        },
		    "created_on": "2021-06-23 11:15:00",
		    "updated_by": {
		        "id": 1
		    },
		    "updated_on": "2021-06-23 11:15:00"
		}
	*/
	@PostMapping("skyzer-guide/referenceGuideCategories")
	public ResponseEntity<ReferenceGuideCategory> createReferenceGuideCategory(@RequestBody ReferenceGuideCategory referenceGuideCategory) {
	 	
	 	try {
	 		ReferenceGuideCategory newReferenceGuideCategory = referenceGuideCategoryDAO.create(referenceGuideCategory);
	 		
	 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newReferenceGuideCategory.getId()).toUri();
		 	return ResponseEntity.created(location).build();
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("skyzer-guide/referenceGuideCategories/{id}")
	public ResponseEntity<Object> deleteReferenceGuideCategory(@PathVariable Integer id) {
		try {
	 		boolean isDeleted =	referenceGuideCategoryDAO.delete(id);
	 		
	 		if(!isDeleted) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			else return new ResponseEntity<>("", HttpStatus.OK); 
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
