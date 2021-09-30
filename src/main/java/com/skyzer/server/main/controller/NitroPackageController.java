package com.skyzer.server.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.skyzer.server.main.DAO.nitroPackageDAO;

@RestController
public class NitroPackageController {

	@Autowired
	private nitroPackageDAO nitroPackageDAO;
	
	@GetMapping("skyzer-guide/nitroPackage/sendLink/{email}")
	public ResponseEntity<Object> getDivisionByDivision(@PathVariable String email) {
		
		try {
			Boolean isSuccess = nitroPackageDAO.find(email);
			
			if(!isSuccess) return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
			else return new ResponseEntity<>("", HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
