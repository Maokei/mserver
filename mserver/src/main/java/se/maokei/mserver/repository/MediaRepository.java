package se.maokei.mserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;

import java.util.UUID;

@Repository
public interface MediaRepository extends ReactiveCrudRepository<Media, UUID> {
  Mono<Media> findByForeignId(String foreignId);
  Mono<Media> findByLocation(String location);
}
