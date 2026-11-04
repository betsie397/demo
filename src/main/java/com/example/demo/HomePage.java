package com.example.demo;

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

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;

@Controller 
public class HomePage {
	private final LogRepository logRepository;
	private final InstrumentRepository instrumentRepository;

	public HomePage(LogRepository logRepository, InstrumentRepository instrumentRepository){
		this.logRepository = logRepository;
		this.instrumentRepository = instrumentRepository;
	}

	@GetMapping("/home")
	public String home(Model model) throws IOException{
		model.addAttribute("practiceLog", new Log());

		LocalDate date = LocalDate.now();
		model.addAttribute("today", date);

		AccessLog.ReadResult result = AccessLog.home();
		int lastId = result.lastId();
		List<Map<String, String>> rows = result.rows();
		model.addAttribute("id", lastId);
		model.addAttribute("data", rows);

		List<String> instruments = instrumentRepository.findAll().stream().map(Instrument::getInstrumentName).toList();
		model.addAttribute("options", instruments);

		return "home";
	}

	@PostMapping("/home")
	public String updated(@RequestParam String date, @RequestParam String duration, @RequestParam String instrument, @RequestParam boolean scales, 
		@RequestParam boolean exercises, @RequestParam boolean pieces, @RequestParam String description, Model model) throws IOException{
		
		logRepository.save(new Log(date, duration, instrument, scales, exercises, pieces, description, LocalDate.now()));

		return home(model);
	}

	@DeleteMapping("delete/{id}")
	public String deleted(@PathVariable int id, Model model) throws IOException{
		AccessLog.deleteEntry(id);
		return home(model);
	}

	@PostMapping("/instruments")
	public String instruments(@RequestParam String instrumentName, Model model) throws IOException{
		instrumentRepository.save(new Instrument(instrumentName));
		return home(model);
	}

	@PutMapping("/edit/{id}")
	public String edit(@PathVariable int id, @ModelAttribute("practiceLog") Log data, Model model) throws IOException{
		AccessLog.updateEntry(id, data);

		return home(model);
	}

}