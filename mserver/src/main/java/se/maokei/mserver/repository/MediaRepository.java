package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;

@Repository
public interface MediaRepository extends ReactiveMongoRepository<Media, String> {
  Mono<Media> findByForeignId(String foreignId);
}
