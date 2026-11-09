package se.maokei.mserver.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("playlists")
public class Playlist extends EntityMetadata {
    @Schema(name = "id", description = "The generated ID when saved into the database")
    @Id
    @Column("playlist_id")
    private UUID playlistId;

    @Column("user_id")
    private UUID userId;

    private String title;
    private String description;

    @Column("is_public")
    private boolean isPublic;

    @Column("is_collaborative")
    private boolean isCollaborative;

    public Playlist(UUID userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Playlist(UUID userId, String title, String description, boolean isPublic, boolean isCollaborative) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.isPublic = isPublic;
        this.isCollaborative = isCollaborative;
    }

    @Override
    public void generateId() {
        if (playlistId == null) {
            this.playlistId = UUID.randomUUID();
        }
    }
}