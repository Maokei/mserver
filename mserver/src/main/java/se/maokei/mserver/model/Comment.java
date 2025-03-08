package se.maokei.mserver.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table("comments")
public class Comment extends EntityMetadata {
  @Schema(name = "user_id", description = "User that created comment")
  private UUID user_id;
  @Schema(name = "comment", description = "Comment text")
  private String comment;
  // user
  // children
  // parent_id
  // likes []

  public record CommentDto(UUID id, UUID user_id, String comment) {
    public Comment toComment() {
      return Comment.builder().user_id(id).user_id(user_id).comment(comment).build();
    }
  }
}
