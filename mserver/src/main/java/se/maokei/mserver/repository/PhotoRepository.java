package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import se.maokei.mserver.model.Photo;

@Repository
public interface PhotoRepository extends ReactiveMongoRepository<Photo, String> { }
