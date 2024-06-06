package se.maokei.mserver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table
public class Playlist extends EntityMetadata {
    private String playlistName;

    public Playlist() {
        this.media = new ArrayList<>();
        this.medias = new ArrayList<>();
    }

    public Playlist(String playlistName, List<UUID> medias) {
        this.playlistName = playlistName;
        this.medias = medias;
    }

    private List<Media> media;
    private List<UUID> medias;

    public void addMedia(Media media) {
        this.media.add(media);
    }

    public void addMediaId(UUID id) {
        this.medias.add(id);
    }

    public void addMediaId(Media media) {
        this.medias.add(media.getId());
    }
}
