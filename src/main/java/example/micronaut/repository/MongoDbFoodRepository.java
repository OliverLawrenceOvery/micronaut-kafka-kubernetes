package example.micronaut.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import example.micronaut.domain.Food;
import example.micronaut.configuration.MongoDbConfiguration;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public class MongoDbFoodRepository implements FoodRepository {

    private final MongoDbConfiguration mongoConf;
    private final MongoClient mongoClient;

    public MongoDbFoodRepository(MongoDbConfiguration mongoConf,
                                 MongoClient mongoClient) {
        this.mongoConf = mongoConf;
        this.mongoClient = mongoClient;
    }

    @Override
    public Mono<Boolean> save(@NonNull @NotNull @Valid Food food){
        return Mono.from(getCollection().insertOne(food))
                .map(insertOneResult -> Boolean.TRUE)
                .onErrorReturn(Boolean.FALSE);
    }

    @Override
    @NonNull
    public Publisher<Food> list() {
        return getCollection().find();
    }
    
    @NonNull
    private MongoCollection<Food> getCollection(){
        return mongoClient.getDatabase(mongoConf.getName())
                .getCollection(mongoConf.getCollection(), Food.class);
    }
}
