package ru.weather.ws.core;

public class WeatherCreatedEvent {
    private String weatherId;
    private String city;
    private Integer temperature;
    private String state;

    public WeatherCreatedEvent(String weatherId, String city, Integer temperature, String state) {
        this.weatherId = weatherId;
        this.city = city;
        this.temperature = temperature;
        this.state = state;
    }

    public WeatherCreatedEvent() {
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
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
