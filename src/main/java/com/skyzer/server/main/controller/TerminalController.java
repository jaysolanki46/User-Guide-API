package com.skyzer.server.main.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skyzer.server.main.NotFoundException;
import com.skyzer.server.main.DAO.terminalDAO;
import com.skyzer.server.main.bean.Terminal;

@RestController
public class TerminalController {
	
	@Autowired
	private terminalDAO terminalDAO;

	@GetMapping("skyzer-terminal/terminals")
	public List<Terminal> getAllTerminals() {
		
		return terminalDAO.findAll();
	}
	
	@GetMapping("skyzer-terminal/terminals/{id}")
	public Terminal getTerminalInfo(@PathVariable Long id) {
		
		Terminal terminal = terminalDAO.find(id);
		
		if(terminal == null) 
			throw new NotFoundException("id:" + id);
		
		return terminal;
	}
	
	@PostMapping("skyzer-terminal/terminals")
	public ResponseEntity<Object> createTerminal(@RequestBody Terminal terminal) {

	 	Terminal newTerminal =	terminalDAO.save(terminal);
	 	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTerminal.getId()).toUri();
	 	return ResponseEntity.created(location).build();
	 	
	}
	
	@DeleteMapping("skyzer-terminal/terminals/{id}")
	public void deleteTerminal(@PathVariable Long id) {
		Terminal terminal = terminalDAO.delete(id);
		if(terminal == null) 
			throw new NotFoundException("id:" + id);
	}
}
