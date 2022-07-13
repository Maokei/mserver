package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import se.maokei.mserver.model.Image;

@Repository
public interface ImageDbRepository extends ReactiveMongoRepository<Image, String> {}

