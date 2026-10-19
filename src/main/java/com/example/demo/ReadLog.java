package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;


@Controller 
public class ReadLog {

	public static List<Map<String, String>> home() {
		List<Map<String, String>> rows = new ArrayList<>();

		ClassPathResource csvFile = new ClassPathResource("static/practice_log.csv");

		try (InputStream inputStream = csvFile.getInputStream();
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			String headers = reader.readLine();
			String[] headings = headers.split(",", -1);
			String line;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

				String[] values = line.split(", ", -1);
				Map<String, String> row = new LinkedHashMap<>();

				for (int i = 0; i < headings.length; i++) {
					String heading = headings[i].trim();
					String value = i < values.length ? values[i].trim() : "";
					row.put(heading, value);
				}
				rows.add(row);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(rows);

		return rows;
	}


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