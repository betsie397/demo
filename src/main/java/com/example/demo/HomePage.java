package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.repository.LogRepository;
import com.example.demo.entity.Log;
import com.example.demo.repository.InstrumentRepository;
import com.example.demo.entity.Instrument;
import com.example.demo.repository.UserInstrumentRepository;
import com.example.demo.entity.UserInstrument;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;

@Controller 
public class HomePage {
	private final LogRepository logRepository;
	private final InstrumentRepository instrumentRepository;
	private final UserInstrumentRepository userInstrumentRepository;

	public HomePage(LogRepository logRepository, InstrumentRepository instrumentRepository, UserInstrumentRepository userInstrumentRepository){
		this.logRepository = logRepository;
		this.instrumentRepository = instrumentRepository;
		this.userInstrumentRepository = userInstrumentRepository;
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session, HttpServletResponse response) throws IOException{
		response.setHeader("Cache-Control", "no-store");
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) { return "redirect:/login"; }

		LocalDate date = LocalDate.now();
		model.addAttribute("today", date);

		List<Log> rows = logRepository.findByUserId(userId);
		model.addAttribute("data", rows);

		List<Instrument> instruments = instrumentRepository.findAll();
		model.addAttribute("instruments", instruments);

		List<UserInstrument> options = userInstrumentRepository.findByUserId(userId);
		model.addAttribute("options", options);

		return "home";
	}

	@PostMapping("/home")
	public String updated(@RequestParam String date, @RequestParam String duration, @RequestParam String instrument, 
		@RequestParam(defaultValue = "false") boolean scales, @RequestParam(defaultValue = "false") boolean exercises, 
		@RequestParam(defaultValue = "false") boolean pieces, @RequestParam String description, Model model, HttpSession session) throws IOException{
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) { return "redirect:/login"; }

		Instrument selected = instrumentRepository.findByInstrumentName(instrument).orElseThrow(() ->
			new IllegalArgumentException("Instrument not found"));
		
		Log log = new Log(date, duration, selected.getInstrumentId(), scales, exercises, pieces, description, LocalDate.now());
		log.setUserId(userId);
		logRepository.save(log);

		return "redirect:/home";
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity deleted(@PathVariable int id, Model model) throws IOException{
		logRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/instruments")
	public String instruments(@RequestParam String instrumentName, Model model) throws IOException{
		instrumentRepository.save(new Instrument(instrumentName));
		return "redirect:/home";
	}

	@PutMapping("/edit/{id}")
	@ResponseBody
	public ResponseEntity edit(@PathVariable int id, @RequestParam String date, @RequestParam String duration, @RequestParam String instrument, 
		@RequestParam(defaultValue = "false") boolean scales, @RequestParam(defaultValue = "false") boolean exercises, 
		@RequestParam(defaultValue = "false") boolean pieces, @RequestParam String description, Model model) throws IOException{
		Instrument selected = instrumentRepository.findByInstrumentName(instrument).orElseThrow(() ->
			new IllegalArgumentException("Instrument not found"));

		Log log = logRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Log not found"));

		log.setDate(date);
		log.setDuration(duration);
		log.setInstrument(selected.getInstrumentId());
		log.setScales(scales);
		log.setExercises(exercises);
		log.setPieces(pieces);
		log.setDescription(description);

		logRepository.save(log);
		return ResponseEntity.noContent().build();
	}
}