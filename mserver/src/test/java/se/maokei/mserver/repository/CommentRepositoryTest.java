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
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;

import java.util.List;
import java.util.UUID;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentRepositoryTest {
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private UserRepository userRepository;

  private User testUser = new User("test-user", "password", "test@mserver.com", List.of(Role.ROLE_USER), true, true);

  @Test
  void saveCommentTest() {
    String txt = "New comment";
    var persistedUser = userRepository.save(testUser).block();
    Comment c = Comment.builder().userId(persistedUser.getId()).comment(txt).build();

    var persisted = commentRepository.save(c)
            .doOnNext(a -> commentRepository.findByCommentId(a.getCommentId()));
    StepVerifier.create(persisted)
            .assertNext(
                    f -> Assertions.assertEquals(txt, f.getComment(), "Strings don't match")
            ).verifyComplete();
  }

  @Test
  public void saveCommentAndDeleteTest() {
    var persistedUser = userRepository.save(testUser).block();
    String text = "A comment for testing";
    Comment comment = Comment.builder()
            .comment(text)
            .userId(persistedUser.getId())
            .build();

    Mono<UUID> setup = commentRepository.save(comment).map(Comment::getCommentId);
    Mono<Comment> mono = Mono.from(setup).flatMap(id -> commentRepository.findById(id));

    StepVerifier.create(mono).consumeNextWith(c -> {
      Assertions.assertEquals(text, c.getComment(), "Text is not the same.");
    }).verifyComplete();
  }

  @Test
  public void updateCommentTest() {
    var persistedUser = userRepository.save(testUser).block();
    String text = "update";
    String text2 = "update1";
    Comment comment = Comment.builder()
            .comment(text)
            .userId(persistedUser.getId())
            .build();

    Mono<Comment> setup = commentRepository.save(comment)
            .doOnNext(c -> commentRepository.findById(c.getCommentId()).subscribe())
            .doOnNext(m -> m.setComment(text2))
            .doOnNext(u -> commentRepository.save(u).subscribe())
            .doOnNext(f -> commentRepository.findById(f.getCommentId()).subscribe());
    StepVerifier.create(setup).assertNext(
            a -> {
              Assertions.assertEquals(text2, a.getComment(), "Comment was not updated");
              Assertions.assertNotNull(a.getCommentId());
            }
    ).verifyComplete();
  }
}