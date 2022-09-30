package se.maokei.mserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Playlist extends EntityMetadata {
    private String playlistName;
    @DBRef
    private List<Media> media;
}
