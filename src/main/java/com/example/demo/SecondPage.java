package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller 
public class SecondPage {

	@GetMapping("/second")
	public String home() throws IOException{
		return "second";
	}

}