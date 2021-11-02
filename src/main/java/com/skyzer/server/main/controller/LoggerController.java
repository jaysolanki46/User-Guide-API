package com.skyzer.server.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.skyzer.server.main.DAO.LoggerDAO;
import com.skyzer.server.main.bean.Logger;

@RestController
public class LoggerController {

	@Autowired
	private LoggerDAO loggerDAO;
	
	@PostMapping("/log")
	public ResponseEntity<?> createAuthToken(@RequestBody Logger logger) {

		try {
			Boolean logged = loggerDAO.create(logger);

			if(!logged) return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			else return new ResponseEntity<>("", HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
