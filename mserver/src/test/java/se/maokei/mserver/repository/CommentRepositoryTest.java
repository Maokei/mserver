package se.maokei.mserver.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.model.Comment;

import java.util.UUID;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentRepositoryTest {
  @Autowired
  private CommentRepository commentRepository;

  @Test
  void saveTest() {
    UUID id = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    Comment c = new Comment();
    c.setId(id);
    c.setUser_id(userId);
    c.setComment("My comment");

    var persisted = commentRepository.save(c)
            .map(a -> commentRepository.findById(a.getId()))
            .flatMap(a -> a.map(b -> b.getId()));

    StepVerifier.create(persisted).expectNext(id).verifyComplete();
  }

  @Test
  public void saveCommentTest() {
    String text = "A comment for testing";
    Comment comment = Comment.builder()
            .comment(text)
            .build();
    UUID id = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    comment.setId(id);
    comment.setUser_id(userId);

    Mono<String> setup = commentRepository.save(comment).map(c -> c.getComment());
    Mono<Comment> found = commentRepository.findById(id);
    var composite = Mono.from(setup).thenMany(found);

    StepVerifier.create(composite).consumeNextWith(c -> {
      Assertions.assertEquals(text, c.getComment(), "Text is not the same.");
    }).verifyComplete();
  }

  @Test
  public void updateCommentTest() {
    UUID id = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    String text = "First";
    Comment comment = Comment.builder()
            .comment(text)
            .build();
    comment.setId(id);
    comment.setUser_id(userId);

    Mono<Comment> setup = commentRepository.save(comment);
    //Mono<Comment> update = commentRepository.save()
    //var composite = Mono.from(setup).thenMany(found);
  }
}
