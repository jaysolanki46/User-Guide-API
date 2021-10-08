package com.skyzer.server.main.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skyzer.server.main.DAO.bulletinDAO;
import com.skyzer.server.main.bean.Bulletin;

@RestController
public class BulletinController {

	@Autowired
	private bulletinDAO bulletinDAO;
	
	@GetMapping("skyzer-guide/bulletins")
	public ResponseEntity<List<Bulletin>> getAllBulletin() {
		
		try {
			List<Bulletin> bulletins = bulletinDAO.findAll();
			if(bulletins.isEmpty() || bulletins == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(bulletins, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
}
