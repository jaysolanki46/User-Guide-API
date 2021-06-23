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
import com.skyzer.server.main.DAO.referenceGuideFunctionDAO;
import com.skyzer.server.main.bean.ReferenceGuideFunction;

@RestController
public class ReferenceGuideFunctionController {

	@Autowired
	private referenceGuideFunctionDAO referenceGuideFunctionDAO;
	
	@GetMapping("skyzer-guide/referenceGuideFunctions")
	public ResponseEntity<List<ReferenceGuideFunction>> getAllReferenceGuideFunctions() {
		
		try {
			List<ReferenceGuideFunction> referenceGuideFunctions = referenceGuideFunctionDAO.findAll();
			if(referenceGuideFunctions.isEmpty() || referenceGuideFunctions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(referenceGuideFunctions, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/referenceGuideFunctions/{id}")
	public ResponseEntity<ReferenceGuideFunction> getReferenceGuideFunction(@PathVariable Integer id) {
		
		try {
			ReferenceGuideFunction referenceGuideFunction = referenceGuideFunctionDAO.find(id);

			if(referenceGuideFunction == null) return new ResponseEntity<>(referenceGuideFunction, HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>(referenceGuideFunction, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	/** POST PATTERN FROM POSTMAN
		 {
		    "name": "Battery - Display Status",
		    "short_solution": "20",
		    "long_solution": "long_solution 20",
		    "note": "TETRA ONLY",
		    "reference_guide_category": {
		        "id": 1
		    },
		    "created_by": {
		        "id": 1
		    },
		    "created_on": "2021-06-23 00:00:00",
		    "updated_by": {
		        "id": 1
		    },
		    "updated_on": "2021-06-23 12:30:01"
		}
	 */
	@PostMapping("skyzer-guide/referenceGuideFunctions")
	public ResponseEntity<ReferenceGuideFunction> createReferenceGuideFunction(@RequestBody ReferenceGuideFunction referenceGuideFunction) {
	 	
	 	try {
	 		ReferenceGuideFunction newReferenceGuideFunction = referenceGuideFunctionDAO.create(referenceGuideFunction);
	 		
	 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newReferenceGuideFunction.getId()).toUri();
		 	return ResponseEntity.created(location).build();
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("skyzer-guide/referenceGuideFunctions/{id}")
	public ResponseEntity<Object> deleteReferenceGuideFunction(@PathVariable Integer id) {
		try {
	 		boolean isDeleted =	referenceGuideFunctionDAO.delete(id);
	 		
	 		if(!isDeleted) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			else return new ResponseEntity<>("", HttpStatus.OK); 
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
