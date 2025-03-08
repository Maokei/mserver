package se.maokei.mserver.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.PageRequest;
import se.maokei.mserver.model.Media;

import java.util.UUID;

@Repository
public interface MediaRepository extends ReactiveSortingRepository<Media, UUID> {
  Mono<Media> findById(UUID id);
  Flux<Media> findAllBy(PageRequest pr);
  Mono<Media> findByForeignId(String foreignId);
  Mono<Media> findByLocation(String location);

  Mono<Media> save(Media media);
}
