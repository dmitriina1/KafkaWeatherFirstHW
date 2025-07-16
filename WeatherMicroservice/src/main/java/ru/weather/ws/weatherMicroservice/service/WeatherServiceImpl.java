package ru.weather.ws.weatherMicroservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.weather.ws.core.WeatherEvent;
import ru.weather.ws.core.dto.WeatherDto;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class WeatherServiceImpl implements WeatherService {


    private KafkaTemplate<String, WeatherEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public WeatherServiceImpl(KafkaTemplate<String, WeatherEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createWeather(WeatherDto weatherDto){

        String weatherId = UUID.randomUUID().toString();

        WeatherEvent weatherEvent = new WeatherEvent(weatherId, weatherDto);


//        SendResult<String, WeatherEvent> result= kafkaTemplate
//                .send("product-created-events-topic", weatherId, weatherEvent).get();
////        LOGGER.info("Message sent successfully {}", result.getRecordMetadata());
//        LOGGER.info("Topic {}", result.getRecordMetadata().topic());
//        LOGGER.info("Partition {}", result.getRecordMetadata().partition());
//        LOGGER.info("Offset {}", result.getRecordMetadata().offset());
        //АСинхронная
        CompletableFuture<SendResult<String, WeatherEvent>> future= kafkaTemplate
                .send("weather-events-topic", weatherId, weatherEvent);

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
