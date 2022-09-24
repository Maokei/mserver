package se.maokei.mserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Playlist extends EntityMetadata {
    private String playlistName;
    private List<Media> mediaList;
}
