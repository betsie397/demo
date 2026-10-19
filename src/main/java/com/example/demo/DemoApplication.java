package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.CSVReader;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
		
		String csvFile = "practice_log.csv";
		CSVReader csvReader = new CSVReader(reader);
		String[] record;
		String[][] lines;
        while ((record = csvReader.readNext()) != null) {
            for (String cell : record) {
                lines[]
            }
        }

    }

	@GetMapping("index.html")
	public String homePage(@RequestParams(data = lines, options = instruments)) {
		return "index";
	}

}
