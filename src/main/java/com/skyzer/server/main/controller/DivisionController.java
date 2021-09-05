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
import com.skyzer.server.main.DAO.divisionDAO;
import com.skyzer.server.main.bean.Division;

@RestController
public class DivisionController {

	@Autowired
	private divisionDAO divisionDAO;
	
	@GetMapping("skyzer-guide/divisions")
	public ResponseEntity<List<Division>> getAllDivisions() {
		
		try {
			List<Division> divisions = divisionDAO.findAll();
			if(divisions.isEmpty() || divisions == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(divisions, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping("skyzer-guide/division/{divisionAccount}")
	public ResponseEntity<Division> getDivision(@PathVariable Integer divisionAccount) {
		
		try {
			Division division = divisionDAO.find(divisionAccount);

			if(division == null) return new ResponseEntity<>(division, HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>(division, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@PostMapping("skyzer-guide/divisions")
	public ResponseEntity<Division> createDivision(@RequestBody Division division) {
	 	
	 	try {
	 		Division newDivision =	divisionDAO.create(division);
	 		
	 		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDivision.getId()).toUri();
		 	return ResponseEntity.created(location).build();
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@DeleteMapping("skyzer-guide/divisions/{id}")
	public ResponseEntity<Object> deleteDivision(@PathVariable Integer id) {
		try {
	 		boolean isDeleted =	divisionDAO.delete(id);
	 		
	 		if(!isDeleted) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			else return new ResponseEntity<>("", HttpStatus.OK); 
	 		
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
