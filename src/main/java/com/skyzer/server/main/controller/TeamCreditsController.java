package com.skyzer.server.main.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyzer.server.main.DAO.teamCreditsDAO;
import com.skyzer.server.main.bean.Team;

@RestController
public class TeamCreditsController {

	@Autowired
	private teamCreditsDAO teamCreditsDAO;
	
	@GetMapping("skyzer-guide/teamCredits")
	public ResponseEntity<List<Team>> getAllTeam() {
		
		try {
			List<Team> team = teamCreditsDAO.findAll();

			if(team.isEmpty() || team == null) {
				return ResponseEntity.noContent().header("Content-Length", "0").build();
			} else {
				return new ResponseEntity<>(team, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().header("Content-Length", "0").build();
		} 
	}
	
}
