package se.maokei.mserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "Media", description = "Generic media class, for media files")
@Table("medias")
public class Media extends EntityMetadata {
    @Schema(name = "id", description = "The generated ID when saved into the database")
    @Column("media_id")
    @Id
    private UUID mediaId;
    @Column("foreign_id")
    private String foreignId;
    private String title;
    private Integer views;
    private String url;
    @Column("user_id")
    private UUID userId;
    private MediaType type;
    private HashMap<String, String> metadata;
    @JsonIgnore
    private String filename;
    private long size;
    private byte[] hash;
    @JsonIgnore
    private byte[] content;
    @JsonIgnore
    private String location;

    @Override
    public void generateId() {
        if (mediaId == null)
            this.mediaId = UUID.randomUUID();
    }

    public enum MediaType {
        IMAGE, AUDIO, VIDEO, STL, OBJ, MARKUP, PDF, EPUB, TEXT, CSV, JSON
    }
}
