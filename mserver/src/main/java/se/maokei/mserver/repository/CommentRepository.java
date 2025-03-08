package se.maokei.mserver.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import se.maokei.mserver.model.Comment;

import java.util.UUID;

@Repository
public interface CommentRepository extends ReactiveCrudRepository<Comment, UUID> {

}
