package ru.weather.ws.core;

import ru.weather.ws.core.dto.State;
import ru.weather.ws.core.dto.WeatherDto;

public class WeatherEvent {
    private String weatherId;
    private WeatherDto weatherDto;

    public WeatherEvent(String weatherId, String city, Double temperature, State state) {
        this.weatherId = weatherId;
        this.weatherDto = new WeatherDto(city, temperature, state);
    }

    public WeatherEvent(String weatherId, WeatherDto weatherDto) {
        this.weatherId = weatherId;
        this.weatherDto = weatherDto;
    }

    public WeatherEvent() {
    }

    public WeatherDto getWeatherDto() {
        return weatherDto;
    }

    public void setWeatherDto(WeatherDto weatherDto) {
        this.weatherDto = weatherDto;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
