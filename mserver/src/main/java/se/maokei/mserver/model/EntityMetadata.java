package se.maokei.mserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class EntityMetadata {
  private static final long serialVersionUID = 1L;
  @Id
  private String id;
  @CreatedDate
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime createdAt;
  @LastModifiedDate
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private LocalDateTime updatedAt;
}
