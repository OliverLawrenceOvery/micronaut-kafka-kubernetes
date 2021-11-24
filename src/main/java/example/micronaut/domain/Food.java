package example.micronaut.domain;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;

@Introspected
public class Food {

    @NonNull
    @NotBlank
    @BsonProperty("name")
    private final String name;

    @Nullable
    @BsonProperty("description")
    private String description;

    public Food(@NonNull String name) {
        this(name, null);
    }

    @Creator
    @BsonCreator
    public Food(@NonNull @BsonProperty("name") String name,
                @Nullable @BsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }
}
