package ru.weather.ws.weatherMicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CreateWeatherDto {
    private String city;
    private Integer temperature;
    private String state;

    public CreateWeatherDto(String city, Integer temperature, String state) {
        this.city = city;
        this.temperature = temperature;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
