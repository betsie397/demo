package com.example.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import com.example.demo.entity.Log;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;


@Controller 
public class AccessLog {
	private static final String[] HEADERS = {"ID", "Date", "Duration", "Instrument", "Scales", "Exercises/Studies", "Pieces", "Description", "Date Entered"};
	private static final Path csvFile = Path.of("src/main/resources/static/practice_log.csv");
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

		try (BufferedWriter writer = Files.newBufferedWriter(
			csvFile,
			StandardCharsets.UTF_8,
			StandardOpenOption.CREATE,
			StandardOpenOption.TRUNCATE_EXISTING);
			CSVPrinter csv = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			csv.printRecord((Object[]) HEADERS);

			for (Map<String, String> row : contents.rows()) {
				if (Integer.parseInt(row.get("ID")) == id) {
					continue;
				}
				csv.printRecord(
					row.get("ID"), row.get("Date"), row.get("Duration"), row.get("Instrument"), row.get("Scales"),
					row.get("Exercises/Studies"), row.get("Pieces"), row.get("Description"), row.get("Date Entered"));
			}}
	}

	public static void updateEntry(int id, Log data) throws IOException{
		Path path = Paths.get("src/main/resources/static/practice_log.csv"); 
		List<String[]> rows = new ArrayList<>();
		boolean found = false;

		try (CSVParser parser = CSVParser.parse(path, StandardCharsets.UTF_8,
				CSVFormat.DEFAULT)) {
			for (CSVRecord record : parser) {
				String[] row = new String[record.size()];
				for (int i = 0; i < record.size(); i++) {
					row[i] = record.get(i);
				}

				if (record.get(0).equals(String.valueOf(id))) {
					row[1] = data.getDate();
					row[2] = data.getDuration();
					row[3] = String.valueOf(data.getInstrumentId());
					row[4] = String.valueOf(data.getScales());
					row[5] = String.valueOf(data.getExercises());
					row[6] = String.valueOf(data.getPieces());
					row[7] = data.getDescription();
					found = true;
				}

				rows.add(row);
        }}

		if (!found) {
        throw new FileNotFoundException("No log entry with ID " + id);
    }

		try (CSVPrinter printer = new CSVPrinter(
				Files.newBufferedWriter(path, StandardCharsets.UTF_8),
				CSVFormat.DEFAULT)) {
			for (String[] row : rows) {
				printer.printRecord((Object[]) row);
			}
		}
	}
}
