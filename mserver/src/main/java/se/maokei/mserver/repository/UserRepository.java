package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
    Mono<Boolean> existsByUsername(String username);
    Mono<User> findByEmail(String email);
}
