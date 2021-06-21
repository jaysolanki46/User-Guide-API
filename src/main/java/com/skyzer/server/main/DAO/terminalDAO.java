package com.skyzer.server.main.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skyzer.server.main.bean.Terminal;

@Component  
public class terminalDAO {

	public static Long cnt = 3l;
	private static List<Terminal> terminals = new ArrayList<>();
	
	static {
		terminals.add(new Terminal(1l, "ICT220", "Telium", "ICT220 Desc"));
		terminals.add(new Terminal(2l, "ICT250", "Telium", "ICT250 Desc"));
		terminals.add(new Terminal(3l, "IWL255", "Telium", "IWL255 Desc"));
	}
	
	public List<Terminal> findAll() {
		return terminals;
	}
	
	public Terminal find(Long id) {
		
		for (Terminal terminal : terminals) {
			if(terminal.getId() == id) {
				return terminal;
			}
		}
		return null;
	}
	
	public Terminal save(Terminal terminal) {
		if(terminal.getId() == null) {
			terminal.setId(++cnt);
		}
		terminals.add(terminal);
		return terminal;
	}
	
	public Terminal delete(Long id) {
		Iterator<Terminal> iterator = terminals.iterator();
		while (iterator.hasNext()) {
			Terminal terminal = iterator.next();
			if (terminal.getId() == id) {
				iterator.remove();
				return terminal; // returns the deleted resource back
			}
		}
		return null;
	}
	
}
