package se.maokei.mserver.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table("playlists")
public class Playlist extends EntityMetadata {
    @Schema(name = "id", description = "The generated ID when saved into the database")
    @Id
    private UUID playlistId;
    private String title;

    public Playlist() {
        this.media = new ArrayList<>();
        this.mediaIds = new ArrayList<>();
    }

    public Playlist(String title, List<UUID> medias) {
        this.title = title;
        this.mediaIds = medias;
    }

    private List<Media> media;
    private List<UUID> mediaIds;

    public void addMedia(Media media) {
        this.media.add(media);
    }

    public void addMediaId(UUID id) {
        this.mediaIds.add(id);
    }

    public void addMediaId(Media media) {
        this.mediaIds.add(media.getMediaId());
    }

    @Override
    public void generateId() {
        if (playlistId == null)
            this.playlistId = UUID.randomUUID();
    }
}