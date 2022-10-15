package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import se.maokei.mserver.model.Playlist;

@Repository
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {
}
