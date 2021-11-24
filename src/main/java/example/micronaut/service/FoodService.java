package example.micronaut.service;

import example.micronaut.domain.Food;
import example.micronaut.kafka.KafkaProducerService;
import example.micronaut.repository.FoodRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
public class FoodService {

    private final FoodRepository foodRepository;
    private final KafkaProducerService kafkaProducerService;

    public FoodService(FoodRepository foodRepository, KafkaProducerService kafkaProducerService) {
        this.foodRepository = foodRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Publisher<Food> getAllFood() {
        kafkaProducerService.sendMessage("Getting all food.");
        return foodRepository.list();
    }

    public Mono<Boolean> save(Food food) {
        kafkaProducerService.sendMessage("Saving food: " + food);
        return foodRepository.save(food);
    }

}
