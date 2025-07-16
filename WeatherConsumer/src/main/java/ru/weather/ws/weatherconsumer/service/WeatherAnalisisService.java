package ru.weather.ws.weatherconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.weather.ws.core.WeatherEvent;

import java.util.*;
@Service
public class WeatherAnalisisService {
    private final List<WeatherEvent> allEvents = new ArrayList<>();
    private final Map<String, List<WeatherEvent>> eventsByCity = new HashMap<>();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public synchronized void addEvent(WeatherEvent event) {
        allEvents.add(event);
        String city = event.getWeatherDto().getCity();
        eventsByCity.computeIfAbsent(city, k -> new ArrayList<>()).add(event);
    }

    public Map<String, Object> getGlobalAnalysis() {
        Map<String, Object> analysis = buildAnalysis(allEvents);
        analysis.remove("Текущая погода");

        return analysis;
    }

    public Map<String, Object> getCityAnalysis(String city) {
        List<WeatherEvent> cityEvents = eventsByCity.get(city);
        if (cityEvents == null || cityEvents.isEmpty()) {
            return Map.of("message", "Нет данных по городу " + city);
        }

        return buildAnalysis(cityEvents);
    }

    private Map<String, Object> buildAnalysis(List<WeatherEvent> events) {
        double avgTemp = events.stream()
                .mapToDouble(x-> x.getWeatherDto().getTemperature())
                .average()
                .orElse(0.0);

        double maxTemp = events.stream()
                .mapToDouble(x-> x.getWeatherDto().getTemperature())
                .max()
                .orElse(0.0);

        double minTemp = events.stream()
                .mapToDouble(x-> x.getWeatherDto().getTemperature())
                .min()
                .orElse(0.0);


        Map<String, Object> analysis = new LinkedHashMap<>();
        analysis.put("Кол-во данных", events.size());
        analysis.put("Средняя температура", avgTemp);
        analysis.put("Максимальная температура", maxTemp);
        analysis.put("Минимальная температура", minTemp);

        return analysis;
    }

    public void printAnalysis() {
        LOGGER.info("-------- Общий анализ --------");
        LOGGER.info("{}", getGlobalAnalysis());

        LOGGER.info("-------- Анализ по городам --------");
        eventsByCity.keySet().forEach(city -> {
            LOGGER.info("Город: {}", city);
            LOGGER.info("{}", getCityAnalysis(city));
        });
    }
}
