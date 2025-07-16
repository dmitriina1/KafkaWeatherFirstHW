package ru.weather.ws.weatherMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherMicroserviceApplication.class, args);
	}

}
