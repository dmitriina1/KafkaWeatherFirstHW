package ru.weather.ws.weatherMicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.weather.ws.core.WeatherEvent;
import ru.weather.ws.core.dto.State;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static java.util.Map.entry;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherProducer {
    private final KafkaTemplate<String, WeatherEvent> kafkaTemplate;
    private final Random random = new Random();

    private static final List<String> CITIES = List.of(
            "Санкт-Петербург", "Ханты-Мансийск", "Тюмень", "Новосибирск", "Москва");

    @Scheduled(fixedRate = 5000)
    public void generateAndSendWeatherEvent() {
        WeatherEvent event = generateWeatherEvent();
        kafkaTemplate.send("weather-events-topic", event.getWeatherDto().getCity(), event);
        log.info("Отправлено: {}", event);
    }

    private WeatherEvent generateWeatherEvent() {

        String city = getRandomCity();

        return new WeatherEvent(
                UUID.randomUUID().toString(),
                city,
                getRandomTemperature(),
                getRandomState()
        );
    }

    private String getRandomCity() {
        return CITIES.stream()
                .skip(random.nextInt(CITIES.size()))
                .findFirst()
                .orElseThrow();
    }

    private State getRandomState() {
        State[] states = State.values();
        int index = random.nextInt(states.length);

        return states[index];
    }

    private double getRandomTemperature() {
        return Math.round(new Random().nextDouble() * 35 * 10) / 10.0;
    }

}
