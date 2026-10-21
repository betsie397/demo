package com.example.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.ClassPathResource;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;


@Controller 
public class ReadLog {
	private static final String[] HEADERS = {
        "Date", "Duration", "Instrument", "Scales",
        "Exercises/Studies", "Pieces", "Description", "Date Entered"
    };

	public static List<Map<String, String>> home() throws IOException{
		List<Map<String, String>> rows = new ArrayList<>();
		var csvFile = new ClassPathResource("static/practice_log.csv");
		
		try (Reader reader = new InputStreamReader(
                 csvFile.getInputStream(), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                 .setHeader(HEADERS)
                 .setSkipHeaderRecord(true)
				 .setIgnoreSurroundingSpaces(true)
                 .get()
                 .parse(reader)) {

            for (CSVRecord record : parser) {
                Map<String, String> row = new LinkedHashMap<>();
                for (String header : HEADERS) {
                    row.put(header, record.get(header));
                }
                rows.add(row);
            }
			parser.close();
		}

		return rows;
	}

	public static void writeEntry(String date, String duration, String instrument, String scales, String exercises, String pieces, String description, String dateEntered) throws IOException{
	    Path csvFile = Path.of("data", "practice_log.csv");
		Files.createDirectories(csvFile.getParent());

		try (BufferedWriter writer = Files.newBufferedWriter(
				csvFile,
				StandardCharsets.UTF_8,
				StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
			CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

			csv.printRecord(
					date, duration, instrument, scales,
					exercises, pieces, description, dateEntered);
		}
	};


	public static String[] instruments() {
		
		ClassPathResource txtFile = new ClassPathResource("static/instruments.txt");
		List<String> instruments = new ArrayList<>();

		try (InputStream inputStream = txtFile.getInputStream();
			 BufferedReader reader = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					continue;
				}

				instruments.add(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return instruments.toArray(new String[0]);
	}

}