package se.maokei.mserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import se.maokei.mserver.model.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, Long> {

}
