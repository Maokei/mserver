package se.maokei.mserver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class Playlist extends EntityMetadata {
    private String playlistName;
    //private User author;
    //@DocumentReference(lazy = false)

    public Playlist() {
        this.media = new ArrayList<>();
        this.medias = new ArrayList<>();
    }

    public Playlist(String playlistName, List<String> medias) {
        this.playlistName = playlistName;
        this.medias = medias;
    }

    private List<Media> media;
    private List<String> medias;

    public void addMedia(Media media) {
        this.media.add(media);
    }

    public void addMediaId(String id) {
        this.medias.add(id);
    }

    public void addMediaId(Media media) {
        this.medias.add(media.getId());
    }
}
