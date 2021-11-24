package example.micronaut.repository;

import example.micronaut.domain.Food;
import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface FoodRepository {
    @NonNull
    Publisher<Food> list();

    Mono<Boolean> save(@NonNull @NotNull @Valid Food fruit);
}
