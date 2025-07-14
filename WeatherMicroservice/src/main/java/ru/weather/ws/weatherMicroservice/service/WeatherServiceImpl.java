package ru.weather.ws.weatherMicroservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.weather.ws.core.WeatherCreatedEvent;
import ru.weather.ws.weatherMicroservice.service.dto.CreateWeatherDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Service
public class WeatherServiceImpl implements WeatherService {


    private KafkaTemplate<String, WeatherCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public WeatherServiceImpl(KafkaTemplate<String, WeatherCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createWeather(CreateWeatherDto createWeatherDto){

        String weatherId = UUID.randomUUID().toString();

        WeatherCreatedEvent weatherCreatedEvent = new WeatherCreatedEvent(weatherId,createWeatherDto.getCity(), createWeatherDto.getTemperature(),
                createWeatherDto.getState());


//        SendResult<String, WeatherCreatedEvent> result= kafkaTemplate
//                .send("product-created-events-topic", weatherId, weatherCreatedEvent).get();
////        LOGGER.info("Message sent successfully {}", result.getRecordMetadata());
//        LOGGER.info("Topic {}", result.getRecordMetadata().topic());
//        LOGGER.info("Partition {}", result.getRecordMetadata().partition());
//        LOGGER.info("Offset {}", result.getRecordMetadata().offset());
        //АСинхронная
        CompletableFuture<SendResult<String, WeatherCreatedEvent>> future= kafkaTemplate
                .send("weather-events-topic", weatherId, weatherCreatedEvent);

        future.whenComplete((r, e) -> {
            if(e!=null){
                LOGGER.error("Failed to send message {}", e.getMessage());
            }else {
                LOGGER.info("Message sent successfully {}", r.getRecordMetadata());
            }
        });


        LOGGER.info("Return {}", weatherId);

        return weatherId;
    }
}
