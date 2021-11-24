package example.micronaut.controller;

import example.micronaut.domain.Food;
import example.micronaut.repository.FoodRepository;
import example.micronaut.service.FoodService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/fruits")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Get
    public Publisher<Food> list() {
        return foodService.getAllFood();
    }

    @Post
    public Mono<HttpStatus> save(@NonNull @NotNull @Valid Food food) {
        return foodService.save(food)
                .map(added -> (added) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
