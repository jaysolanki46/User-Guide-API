package com.skyzer.server.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.skyzer.server.main.bean.LogResource;

@RestController
public class LoggerController {

	@PostMapping("/logs/error")
	public ResponseEntity<?> log(@RequestBody LogResource logRS) {
		
		Logger logger = LoggerFactory.getLogger(this.getClass());
		
		try {
			logger.error("Screen: " + logRS.getScreen() + " | " 
					+ "Module: " + logRS.getModule() + " | " 
					+ "Status: " + logRS.getStatus() + " | "
					+ "User: " + logRS.getUser());
			

			return new ResponseEntity<>("", HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
