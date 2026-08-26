package se.maokei.mserver.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
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

    public Playlist() {

    }

    public Playlist(UUID userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public void addMedia(Media media) {
    }

    public void addMediaId(UUID id) {
    }

    public void addMediaId(Media media) {
    }

    @Override
    public void generateId() {
        if (playlistId == null)
            this.playlistId = UUID.randomUUID();
    }
}