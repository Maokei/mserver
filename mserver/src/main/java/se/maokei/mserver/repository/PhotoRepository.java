package se.maokei.mserver.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import se.maokei.mserver.model.Photo;

import java.util.UUID;

@Repository
public interface PhotoRepository extends R2dbcRepository<Photo, UUID> { }
