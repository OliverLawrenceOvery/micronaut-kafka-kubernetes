package example.micronaut.service;

import example.micronaut.domain.Food;
import example.micronaut.kafka.KafkaProducerService;
import example.micronaut.repository.FoodRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Singleton
public class FoodService {

    private static final Logger log = LoggerFactory.getLogger(FoodService.class);
    private final FoodRepository foodRepository;
    private final KafkaProducerService kafkaProducerService;

    public FoodService(FoodRepository foodRepository, KafkaProducerService kafkaProducerService) {
        this.foodRepository = foodRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Publisher<Food> getAllFood() {
        log.info("Getting all food.");
        return foodRepository.list();
    }

    public Mono<Boolean> save(Food food) {
        kafkaProducerService.sendMessage(food);
        return foodRepository.save(food);
    }

}
