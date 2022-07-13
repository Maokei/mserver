package se.maokei.mserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import se.maokei.mserver.model.Comment;
import se.maokei.mserver.repository.CommentRepository;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class CommentRepositoryTest {
  @Autowired
  private CommentRepository commentRepository;

  @Test
  public void saveCommentTest() {
    String text = "A comment for testing";
    Comment comment = Comment.builder()
            .id(123L)
            .comment(text)
            .build();
    commentRepository.save(comment);
    commentRepository.findById(123L).subscribe(found -> {
      System.out.println(found);
      Assertions.assertEquals(text, found.getComment(), "Text is not the same.");
    });
  }
}
