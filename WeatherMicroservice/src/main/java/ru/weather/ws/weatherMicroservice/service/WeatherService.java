package ru.weather.ws.weatherMicroservice.service;

import ru.weather.ws.core.dto.WeatherDto;

public interface WeatherService {
    String createWeather(WeatherDto weatherDto);
}
