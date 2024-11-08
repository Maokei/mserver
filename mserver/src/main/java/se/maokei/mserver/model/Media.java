package se.maokei.mserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(name = "Media", description = "Generic media class, for media files")
@Table
public class Media extends EntityMetadata {
    private String foreignId;
    private String title;
    private Integer views;
    private String url;
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

    public enum MediaType {
        IMAGE, AUDIO, VIDEO
    }
}
