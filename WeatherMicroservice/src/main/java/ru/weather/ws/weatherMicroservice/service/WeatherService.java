package ru.weather.ws.weatherMicroservice.service;

import ru.weather.ws.weatherMicroservice.service.dto.CreateWeatherDto;

import java.util.concurrent.ExecutionException;

public interface WeatherService {
    String createWeather(CreateWeatherDto createWeatherDto);
}
