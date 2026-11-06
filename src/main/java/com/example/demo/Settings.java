package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
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
	private final UserRepository userRepository;

	public Settings(UserInstrumentRepository userInstrumentRepository, InstrumentRepository instrumentRepository, UserRepository userRepository){
		this.userInstrumentRepository = userInstrumentRepository;
		this.instrumentRepository = instrumentRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/settings")
	public String home(Model model, HttpSession session) throws IOException{
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) { return "redirect:/login"; }

		List<UserInstrument> allInstruments = userInstrumentRepository.findAll();
		model.addAttribute("all", allInstruments);

		List<UserInstrument> usersInstruments = userInstrumentRepository.findByUserId(userId);
		model.addAttribute("user", usersInstruments);

		User account = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
		model.addAttribute("account", account);

		List<Instrument> instruments = instrumentRepository.findAll();
		model.addAttribute("instruments", instruments);
		
		return "settings";
	}

	@DeleteMapping("/delete/{instrumentId}/{userId}")
	@ResponseBody
	public ResponseEntity deleted(@PathVariable int instrumentId, @PathVariable int userId) throws IOException{
		UserInstrumentId id = new UserInstrumentId(userId, instrumentId);
		userInstrumentRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/color/{userId}")
	@ResponseBody
	public ResponseEntity colors(@PathVariable int userId, @RequestParam int color) throws IOException{
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
		user.setColor(color);

		userRepository.save(user);
		return ResponseEntity.noContent().build();
	}

}