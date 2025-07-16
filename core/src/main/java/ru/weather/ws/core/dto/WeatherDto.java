package ru.weather.ws.core.dto;

public class WeatherDto {
    private String city;
    private Double temperature;
    private State state;

    public WeatherDto() {}

    public WeatherDto(String city, Double temperature, State state) {
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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setState(String stateStr) {
        if(contains(stateStr)){
            this.state = State.valueOf(stateStr);
        } else {
            throw new IllegalArgumentException("Invalid state: " + stateStr);
        }
    }

    private boolean contains(String stateStr) {
        for (State condition : State.values()) {
            if (condition.name().equals(stateStr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", state='" + state + '\'' +
                '}';
    }
}
