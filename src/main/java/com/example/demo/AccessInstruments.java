package com.example.demo;

import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.List;


@Controller 
public class AccessInstruments {
	private static final Path txtFile = Path.of("src/main/resources/static/instruments.txt");
	public record ReadResult(List<Map<String, String>> rows, int lastId){}

	public static String[] instruments() throws IOException {
		List<String> instruments = Files.readAllLines(txtFile);

		return instruments.toArray(new String[0]);
	}

	public static void newInstrument(String inserted) throws IOException{
		String instrument = inserted.trim();

		Files.writeString(txtFile, 
			System.lineSeparator() + instrument, 
			StandardCharsets.UTF_8, 
			StandardOpenOption.CREATE, 
			StandardOpenOption.APPEND);
	}

}