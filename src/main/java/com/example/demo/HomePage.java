package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;

@Controller 
public class HomePage {

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

		String[] instruments = AccessInstruments.instruments();
		model.addAttribute("options", instruments);

		return "home";
	}

	@PostMapping("/home")
	public String updated(@ModelAttribute("practiceLog") Log data, Model model) throws IOException{
		AccessLog.ReadResult result = AccessLog.home();
		int lastId = result.lastId();
		data.setId(lastId);
		AccessLog.writeEntry(data.getId(), data.getDate(), data.getDuration(), data.getInstrument(), data.getScales(), data.getExercises(), data.getPieces(), data.getDescription(), data.getDateEntered());

		return home(model);
	}

	@DeleteMapping("delete/{id}")
	public String deleted(@PathVariable int id, Model model) throws IOException{
		AccessLog.deleteEntry(id);
		return home(model);
	}

	@PostMapping("/instruments/{inserted}")
	public String instruments(@PathVariable String inserted, Model model) throws IOException{
		AccessInstruments.newInstrument(inserted);
		return home(model);
	}

	@PutMapping("/edit/{id}")
	public String edit(@PathVariable int id, @ModelAttribute("practiceLog") Log data, Model model) throws IOException{
		AccessLog.updateEntry(id, data);

		return home(model);
	}

}