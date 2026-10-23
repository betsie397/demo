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
import java.io.FileWriter;
import java.io.Reader;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;


@Controller 
public class ReadLog {
	private static final String[] HEADERS = {"ID", "Date", "Duration", "Instrument", "Scales", "Exercises/Studies", "Pieces", "Description", "Date Entered"};
	private static final Path csvFile = Path.of("src/main/resources/static/practice_log.csv");
	private static final Path txtFile = Path.of("static/instruments.txt");
	public record ReadResult(List<Map<String, String>> rows, int lastId){}

	public static ReadResult home() throws IOException{
		List<Map<String, String>> rows = new ArrayList<>();
		int lastId = 0;
		
		try (Reader reader = Files.newBufferedReader(
                 csvFile, StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                 .setHeader(HEADERS)
                 .setSkipHeaderRecord(true)
				 .setIgnoreSurroundingSpaces(true)
                 .get()
                 .parse(reader)) {

            for (CSVRecord record : parser) {
				lastId = Integer.parseInt(record.get("ID"));
                Map<String, String> row = new LinkedHashMap<>();
                for (String header : HEADERS) {
                    row.put(header, record.get(header));
                }
                rows.add(row);
            }
		}
		return new ReadResult(rows, lastId);
	}

	public static void writeEntry(int id, String date, String duration, String instrument, String scales, String exercises, String pieces, String description, String dateEntered) throws IOException{
		Files.createDirectories(csvFile.getParent());

		try (BufferedWriter writer = Files.newBufferedWriter(
				csvFile,
				StandardCharsets.UTF_8,
				StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
			CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

			csv.printRecord(
					id, date, duration, instrument, scales,
					exercises, pieces, description, dateEntered);
		}
	};

	public static void deleteEntry(int id) throws IOException{
		ReadResult contents = home();
		List<Map<String, String>> rows = contents.rows();

		try (BufferedWriter writer = Files.newBufferedWriter(
			csvFile,
			StandardCharsets.UTF_8,
			StandardOpenOption.CREATE,
			StandardOpenOption.TRUNCATE_EXISTING);
			CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT)){
				csv.printRecord(HEADERS);
	
				for(Map<String, String> row : rows){
					if (Integer.parseInt(row.get("ID")) != id){
						csv.printRecord(
							row.get("ID"), row.get("Date"), row.get("Duration"), row.get("Instrument"), row.get("Scales"),
							row.get("Exercises/Studies"), row.get("Pieces"), row.get("Description"), row.get("Date Entered"));
						}
			}}
	}


	public static String[] instruments() {
		List<String> instruments = new ArrayList<>();

		try (InputStream inputStream = Files.newInputStream(txtFile);
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

	public static void newInstrument(String inserted) throws IOException{
		String instrument = inserted.trim();

		Files.writeString(txtFile, 
			System.lineSeparator() + instrument, 
			StandardCharsets.UTF_8, 
			StandardOpenOption.CREATE, 
			StandardOpenOption.APPEND);
	}

}