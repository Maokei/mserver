package se.maokei.mserver.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comment extends EntityMetadata {
  private String comment;
}
