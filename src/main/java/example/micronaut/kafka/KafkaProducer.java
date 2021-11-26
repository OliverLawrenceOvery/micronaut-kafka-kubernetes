package example.micronaut.kafka;

import example.micronaut.domain.Food;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.UUID;

@KafkaClient
public interface KafkaProducer {

    @Topic("foodTopic")
    UUID sendMessage(@KafkaKey UUID uuid, Food food);
}
