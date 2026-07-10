package se.maokei.mserver.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table("comments")
public class Comment extends EntityMetadata {
  @Schema(name = "comment_id", description = "The generated ID when saved into the database")
  @Id
  @Column("comment_id")
  private UUID commentId;
  @Schema(name = "user_id", description = "User that created comment")
  @Column("user_id")
  private UUID userId;
  @Schema(name = "comment", description = "Comment text")
  private String comment;

  public record CommentDto(UUID id, UUID user_id, String comment) {
    public Comment toComment() {
      return Comment.builder().userId(id).userId(user_id).comment(comment).build();
    }
  }

  @Override
  public void generateId() {
    if (commentId == null)
      this.commentId = UUID.randomUUID();
  }
}
