package example.micronaut.kafka;

import example.micronaut.domain.Food;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Context;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Singleton
@Context
@KafkaListener
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Topic("foodTopic")
    public Food receiveMessage(@KafkaKey UUID uuid, Food food) {
        log.info("Received food item: " + food.getName() + " with id: " + uuid);
        return food;
    }
}
