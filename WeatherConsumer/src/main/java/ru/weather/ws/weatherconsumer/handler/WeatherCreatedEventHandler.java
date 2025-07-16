package ru.weather.ws.weatherconsumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.weather.ws.core.WeatherEvent;
import ru.weather.ws.weatherconsumer.service.WeatherAnalisisService;

@Component
@KafkaListener(topics = "weather-events-topic", groupId = "weather-events")
public class WeatherCreatedEventHandler {

    private WeatherAnalisisService analysisService = new WeatherAnalisisService();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void handle(WeatherEvent weatherEvent) {
        LOGGER.info("Received event: {}", weatherEvent.getWeatherDto().getCity());

            analysisService.addEvent(weatherEvent);

        analysisService.printAnalysis();
    }
}
