package ru.weather.ws.weatherMicroservice.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.weather.ws.core.dto.WeatherDto;
import ru.weather.ws.weatherMicroservice.service.WeatherService;

import java.util.Date;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<Object> createWeather(@RequestBody WeatherDto weatherDto){
        String weatherID = weatherService.createWeather(weatherDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(weatherID);
    }
}
