package example.micronaut.controller;

import example.micronaut.domain.Fruit;
import example.micronaut.repository.FruitRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/fruits") // <1>
public class FruitController {

    private final FruitRepository fruitService;

    public FruitController(FruitRepository fruitService) {  // <2>
        this.fruitService = fruitService;
    }

    @Get  // <3>
    public Publisher<Fruit> list() {
        return fruitService.list();
    }

    @Post // <4>
    public Mono<HttpStatus> save(@NonNull @NotNull @Valid Fruit fruit) { // <5>
        return fruitService.save(fruit) // <6>
                .map(added -> (added) ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
