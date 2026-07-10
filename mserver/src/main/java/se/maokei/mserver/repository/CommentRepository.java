package se.maokei.mserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Comment;

import java.util.UUID;

@Repository
public interface CommentRepository extends ReactiveCrudRepository<Comment, UUID> {
    Mono<Comment> findByCommentId(UUID id);
}
