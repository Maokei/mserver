package se.maokei.mserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Playlist;

import java.util.UUID;

@Repository
public interface PlaylistRepository extends ReactiveCrudRepository<Playlist, UUID> {
    Mono<Playlist> findByPlaylistId(UUID playlistId);
    Flux<Playlist> findPlaylistByUserId(UUID userId);
}
