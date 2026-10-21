package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.LocalDate;

@Controller 
public class HomePage {

	@GetMapping({"/", "/index"})
	public String home(Model model) throws IOException{
		model.addAttribute("practiceLog", new Log());

		LocalDate date = LocalDate.now();
		model.addAttribute("today", date);

		List<Map<String, String>> map = ReadLog.home();
		model.addAttribute("data", map);

		String[] instruments = ReadLog.instruments();
		model.addAttribute("options", instruments);

		return "index";
	}

	@PostMapping({"/", "/index"})
	public String updated(@ModelAttribute("practiceLog") Log data, Model model) throws IOException{
		System.out.println(data.getDate() + data.getDuration() + data.getInstrument() + data.getScales() + data.getExercises() + data.getPieces() + data.getDescription() + data.getDateEntered());
		//ReadLog.writeEntry(data.getDate(), data.getDuration(), data.getInstrument(), data.getScales(), data.getExercises(), data.getPieces(), data.getDescription(), data.getDateEntered());

		return home(model);
	}

}