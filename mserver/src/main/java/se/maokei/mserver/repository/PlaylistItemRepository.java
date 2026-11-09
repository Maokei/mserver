package se.maokei.mserver.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.PlaylistItem;

import java.util.UUID;

@Repository
public interface PlaylistItemRepository extends ReactiveCrudRepository<PlaylistItem, UUID> {
    @Query("SELECT * FROM playlist_item WHERE playlist_id = :playlistId ORDER BY pos ASC")
    Flux<PlaylistItem> findByPlaylistIdOrderByPositionAsc(UUID playlistId);

    @Query("SELECT COUNT(*) FROM playlist_item WHERE playlist_id = :playlistId")
    Mono<Long> countByPlaylistId(UUID playlistId);

    @Query("SELECT COALESCE(MAX(pos), -1) FROM playlist_item WHERE playlist_id = :playlistId")
    Mono<Integer> findMaxPositionByPlaylistId(UUID playlistId);

    @Query("SELECT EXISTS(SELECT 1 FROM playlist_item WHERE playlist_id = :playlistId AND media_id = :mediaId)")
    Mono<Boolean> existsByPlaylistIdAndMediaId(UUID playlistId, UUID mediaId);

    @Modifying
    @Query("DELETE FROM playlist_item WHERE playlist_id = :playlistId AND media_id = :mediaId")
    Mono<Void> deleteByPlaylistIdAndMediaId(UUID playlistId, UUID mediaId);

    @Modifying
    @Query("DELETE FROM playlist_item WHERE playlist_id = :playlistId AND pos = :position")
    Mono<Void> deleteByPlaylistIdAndPosition(UUID playlistId, int position);

    @Modifying
    @Query("DELETE FROM playlist_item WHERE playlist_id = :playlistId")
    Mono<Void> deleteByPlaylistId(UUID playlistId);

    @Modifying
    @Query("INSERT INTO playlist_item (playlist_id, media_id, pos, created) VALUES (:playlistId, :mediaId, :position, NOW())")
    Mono<Void> insertPlaylistItem(UUID playlistId, UUID mediaId, int position);

    @Modifying
    @Query("UPDATE playlist_item SET pos = pos + 1 WHERE playlist_id = :playlistId AND pos >= :position")
    Mono<Void> shiftPositionsUp(UUID playlistId, int position);

    @Modifying
    @Query("UPDATE playlist_item SET pos = pos - 1 WHERE playlist_id = :playlistId AND pos > :position")
    Mono<Void> shiftPositionsDown(UUID playlistId, int position);

    @Modifying
    @Query("UPDATE playlist_item SET pos = :newPosition WHERE playlist_id = :playlistId AND media_id = :mediaId")
    Mono<Void> updatePosition(UUID playlistId, UUID mediaId, int newPosition);
}
