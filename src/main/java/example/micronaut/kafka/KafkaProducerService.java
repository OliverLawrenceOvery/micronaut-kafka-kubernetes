package example.micronaut.kafka;

import example.micronaut.domain.Food;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Requires(classes = KafkaProducer.class)
@Singleton
@Context
public class KafkaProducerService {

    private final static Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    private KafkaProducer kafkaProducer;

    @Inject
    public KafkaProducerService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public UUID sendMessage(Food food) {
        UUID uuid = UUID.randomUUID();
        log.info("Sending food item: " + food.getName() + " with id: " + uuid);
        kafkaProducer.sendMessage(uuid, food);
        return uuid;
    }
}
