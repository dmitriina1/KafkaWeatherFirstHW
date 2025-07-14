package ru.weather.ws.weatherconsumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.weather.ws.core.WeatherCreatedEvent;

@Component
@KafkaListener(topics = "weather-events-topic", groupId = "weather-events")
public class WeatherCreatedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(WeatherCreatedEvent weatherCreatedEvent) {
        LOGGER.info("Received event: {}", weatherCreatedEvent.getTemperature());
    }
}
