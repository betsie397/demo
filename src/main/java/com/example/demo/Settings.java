package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.repository.UserInstrumentRepository;
import com.example.demo.entity.UserInstrument;
import com.example.demo.entity.UserInstrumentId;
import com.example.demo.repository.InstrumentRepository;
import com.example.demo.entity.Instrument;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller 
public class Settings {
	private final UserInstrumentRepository userInstrumentRepository;
	private final InstrumentRepository instrumentRepository;

	public Settings(UserInstrumentRepository userInstrumentRepository, InstrumentRepository instrumentRepository){
		this.userInstrumentRepository = userInstrumentRepository;
		this.instrumentRepository = instrumentRepository;
	}

	@GetMapping("/settings")
	public String home(Model model, HttpSession session) throws IOException{
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) { return "redirect:/login"; }

		List<UserInstrument> allInstruments = userInstrumentRepository.findAll();
		model.addAttribute("all", allInstruments);

		List<UserInstrument> usersInstruments = userInstrumentRepository.findByUserId(userId);
		model.addAttribute("user", usersInstruments);

		List<Instrument> instruments = instrumentRepository.findAll();
		model.addAttribute("instruments", instruments);
		
		return "settings";
	}

	@DeleteMapping("/delete/{instrumentId}/{userId}")
	@ResponseBody
	public ResponseEntity deleted(@PathVariable int instrumentId, @PathVariable int userId, Model model) throws IOException{
		UserInstrumentId id = new UserInstrumentId(userId, instrumentId);
		userInstrumentRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}