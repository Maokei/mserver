package se.maokei.mserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import se.maokei.mserver.model.Playlist;

import java.util.UUID;

@Repository
public interface PlaylistRepository extends ReactiveCrudRepository<Playlist, UUID> {
}
