package example.micronaut.service;

import example.micronaut.domain.Food;
import example.micronaut.repository.FoodRepository;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Publisher<Food> getAllFood() {
        return foodRepository.list();
    }

    public Mono<Boolean> save(Food food) {
        return foodRepository.save(food);
    }

}
