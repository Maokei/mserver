package se.maokei.mserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("playlist_item")
public class PlaylistItem implements Serializable {
    @Column("playlist_id")
    private UUID playlistId;

    @Column("media_id")
    private UUID mediaId;

    @Column("pos")
    private int position;

    @Column("created")
    private LocalDateTime created;

    public PlaylistItem(UUID playlistId, UUID mediaId, int position) {
        this.playlistId = playlistId;
        this.mediaId = mediaId;
        this.position = position;
        this.created = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistItem item = (PlaylistItem) o;
        return position == item.position &&
                Objects.equals(playlistId, item.playlistId) &&
                Objects.equals(mediaId, item.mediaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, mediaId, position);
    }
}