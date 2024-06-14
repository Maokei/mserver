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
  void saveCommentTest() {
    String txt = "New comment";
    UUID userId = UUID.randomUUID();
    Comment c = Comment.builder().user_id(userId).comment(txt).build();

    var persisted = commentRepository.save(c)
            .doOnNext(a -> commentRepository.findById(a.getId()));
    StepVerifier.create(persisted)
            .assertNext(
                    f -> Assertions.assertEquals(txt, f.getComment(), "Strings don't match")
            ).verifyComplete();
  }

  @Test
  public void saveCommentAndDeleteTest() {
    UUID userId = UUID.randomUUID();
    String text = "A comment for testing";
    Comment comment = Comment.builder()
            .comment(text)
            .user_id(userId)
            .build();

    Mono<UUID> setup = commentRepository.save(comment).map(c -> c.getId());
    var composite = Mono.from(setup).doOnNext(commentRepository::findById);

    //TODO fix test
    //Mono<Comment> found = commentRepository.findById(id);
    //var composite = Mono.from(setup).thenMany(found);
    /*StepVerifier.create(composite).consumeNextWith(c -> {
      Assertions.assertEquals(text, c.getComment(), "Text is not the same.");
    }).verifyComplete();*/
  }

  @Test
  public void updateCommentTest() {
    UUID userId = UUID.randomUUID();
    String text = "update";
    String text2 = "update1";
    Comment comment = Comment.builder()
            .comment(text)
            .user_id(userId)
            .build();

    Mono<Comment> setup = commentRepository.save(comment)
            .doOnNext(s -> commentRepository.findById(s.getId()))
            .doOnNext(m -> m.setComment(text2))
            .doOnNext(u -> commentRepository.save(u))
            .doOnNext(f -> commentRepository.findById(f.getId()));
    StepVerifier.create(setup).assertNext(
            a -> {
              Assertions.assertEquals(text2, a.getComment(), "Comment was not updated");
              Assertions.assertNotNull(a.getId());
            }
    ).verifyComplete();
  }
}
