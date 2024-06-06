package se.maokei.mserver.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.maokei.mserver.model.Comment;

import java.util.UUID;

@SpringBootTest
public class CommentRepositoryTest {
  @Autowired
  private CommentRepository commentRepository;

  //@Test
  public void saveCommentTest() {
    String text = "A comment for testing";
    Comment comment = Comment.builder()
            .comment(text)
            .build();

    commentRepository.save(comment).subscribe(c -> {
      UUID id = c.getId();
      commentRepository.findById(id).subscribe(found -> {
        Assertions.assertEquals(text, found.getComment(), "Text is not the same.");
      });
    });
  }
}
