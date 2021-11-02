package com.skyzer.server.main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skyzer.server.main.DAO.teamDAO;
import com.skyzer.server.main.bean.Team;

@RestController
public class TeamController {

	@Autowired
	private teamDAO teamDAO;
	
	@GetMapping("/team")
	public ResponseEntity<List<Team>> getAllTeam() {
		
		try {
			List<Team> team = teamDAO.findAll();

			if(team.isEmpty() || team == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(team, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
	@GetMapping(
			value = "/images/team/{name}",
			produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable String name) throws IOException {
	    InputStream in = getClass()
	      .getResourceAsStream("/images/team/"+ name);
	    return IOUtils.toByteArray(in);
	}
}
