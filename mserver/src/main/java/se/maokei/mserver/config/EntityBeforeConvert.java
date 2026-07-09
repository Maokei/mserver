package se.maokei.mserver.config;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.EntityMetadata;

import java.time.LocalDateTime;

@Component
public class EntityBeforeConvert implements BeforeConvertCallback<EntityMetadata> {
    @Override
    public Publisher<EntityMetadata> onBeforeConvert(EntityMetadata entity, SqlIdentifier table) {
        entity.generateId();
        if (entity.getCreated() == null) {
           entity.setCreated(LocalDateTime.now());
        }
        entity.setUpdated(LocalDateTime.now());
        return Mono.just(entity);
    }
}
