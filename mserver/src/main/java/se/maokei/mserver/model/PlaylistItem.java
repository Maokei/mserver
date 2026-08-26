package se.maokei.mserver.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Table("playlist_item")
public class PlaylistItem implements Serializable {
   @Column("playlist_id")
   private UUID playlistId;
   @Column("media_id")
   private UUID mediaId;
   @Column("pos")
   private int position;

   public PlaylistItem() {}

   public PlaylistItem(UUID playlistId, UUID mediaId, int position) {
      this.playlistId = playlistId;
      this.mediaId = mediaId;
      this.position = position;
   }

   @Override
   public int hashCode() {
      return Objects.hash(playlistId, mediaId, position);
   }
}