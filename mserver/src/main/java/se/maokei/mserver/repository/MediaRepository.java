package se.maokei.mserver.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;

import java.util.UUID;

@Repository
public interface MediaRepository extends R2dbcRepository<Media, UUID> {
  Mono<Media> findByForeignId(String foreignId);
  Mono<Media> findByLocation(String location);
}
