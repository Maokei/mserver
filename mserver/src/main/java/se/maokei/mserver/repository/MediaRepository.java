package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import se.maokei.mserver.model.Image;

public interface MediaMetaRepository extends ReactiveMongoRepository<Image, String> {
}
