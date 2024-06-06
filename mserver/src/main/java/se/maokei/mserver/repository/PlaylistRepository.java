package se.maokei.mserver.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import se.maokei.mserver.model.Playlist;

import java.util.UUID;

@Repository
public interface PlaylistRepository extends R2dbcRepository<Playlist, UUID> {
}
