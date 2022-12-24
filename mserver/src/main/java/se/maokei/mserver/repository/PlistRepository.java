package se.maokei.mserver.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Playlist;

@Component
@RequiredArgsConstructor
public class PlistRepository {
    private final ReactiveMongoTemplate template;
    public Mono<Playlist> save(Playlist playlist) {
        template.insert(playlist);
        return Mono.empty();
    }

}
