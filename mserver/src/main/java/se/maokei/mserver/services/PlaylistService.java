package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.playlist.*;
import se.maokei.mserver.exception.NoSuchMediaException;
import se.maokei.mserver.exception.PlaylistAccessDeniedException;
import se.maokei.mserver.exception.PlaylistNotFoundException;
import se.maokei.mserver.model.Playlist;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.repository.PlaylistItemRepository;
import se.maokei.mserver.repository.PlaylistRepository;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistItemRepository playlistItemRepository;
    private final MediaRepository mediaRepository;

    public Mono<PlaylistResponseDto> createPlaylist(PlaylistCreateDto dto, UUID userId) {
        Playlist playlist = Playlist.builder()
                .userId(userId)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .isPublic(dto.isPublic())
                .isCollaborative(dto.isCollaborative())
                .build();

        playlist.generateId();

        return playlistRepository.save(playlist)
                .map(saved -> mapToResponseDto(saved, 0L));
    }

    public Mono<PlaylistResponseDto> getPlaylist(UUID playlistId, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isReadable(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Access denied to playlist " + playlistId)))
                .flatMap(pl -> playlistItemRepository.countByPlaylistId(playlistId)
                        .map(count -> mapToResponseDto(pl, count))
                );
    }

    public Mono<PlaylistDetailResponseDto> getPlaylistDetails(UUID playlistId, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isReadable(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Access denied to playlist " + playlistId)))
                .flatMap(pl -> playlistItemRepository.findByPlaylistIdOrderByPositionAsc(playlistId)
                        .flatMap(item -> mediaRepository.findByMediaId(item.getMediaId())
                                .map(media -> PlaylistItemDetailDto.builder()
                                        .position(item.getPosition())
                                        .mediaId(media.getMediaId())
                                        .title(media.getTitle())
                                        .filename(media.getFilename())
                                        .location(media.getLocation())
                                        .url(media.getUrl())
                                        .type(media.getType())
                                        .addedAt(item.getCreated())
                                        .build()
                                )
                        )
                        .collectList()
                        .map(items -> PlaylistDetailResponseDto.builder()
                                .playlistId(pl.getPlaylistId())
                                .userId(pl.getUserId())
                                .title(pl.getTitle())
                                .description(pl.getDescription())
                                .isPublic(pl.isPublic())
                                .isCollaborative(pl.isCollaborative())
                                .items(items)
                                .created(pl.getCreated())
                                .updated(pl.getUpdated())
                                .build()
                        )
                );
    }

    public Flux<PlaylistResponseDto> getAllPlaylists() {
        return playlistRepository.findAll()
                .flatMap(pl -> playlistItemRepository.countByPlaylistId(pl.getPlaylistId())
                        .map(count -> mapToResponseDto(pl, count))
                );
    }

    public Flux<PlaylistResponseDto> getUserPlaylists(UUID userId) {
        return playlistRepository.findByUserId(userId)
                .flatMap(pl -> playlistItemRepository.countByPlaylistId(pl.getPlaylistId())
                        .map(count -> mapToResponseDto(pl, count))
                );
    }

    public Flux<PlaylistResponseDto> getPublicPlaylists() {
        return playlistRepository.findByIsPublicTrue()
                .flatMap(pl -> playlistItemRepository.countByPlaylistId(pl.getPlaylistId())
                        .map(count -> mapToResponseDto(pl, count))
                );
    }

    public Mono<PlaylistResponseDto> updatePlaylist(UUID playlistId, PlaylistUpdateDto dto, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isWritable(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Cannot edit playlist " + playlistId)))
                .flatMap(pl -> {
                    if (dto.getTitle() != null) {
                        pl.setTitle(dto.getTitle());
                    }
                    if (dto.getDescription() != null) {
                        pl.setDescription(dto.getDescription());
                    }
                    if (dto.getIsPublic() != null) {
                        pl.setPublic(dto.getIsPublic());
                    }
                    if (dto.getIsCollaborative() != null) {
                        pl.setCollaborative(dto.getIsCollaborative());
                    }
                    return playlistRepository.save(pl);
                })
                .flatMap(saved -> playlistItemRepository.countByPlaylistId(playlistId)
                        .map(count -> mapToResponseDto(saved, count))
                );
    }

    public Mono<UUID> deletePlaylist(UUID playlistId, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isOwner(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Only playlist owner can delete playlist " + playlistId)))
                .flatMap(pl -> playlistItemRepository.deleteByPlaylistId(playlistId)
                        .then(playlistRepository.deleteById(playlistId))
                        .thenReturn(playlistId)
                );
    }

    public Mono<PlaylistDetailResponseDto> addMediaToPlaylist(UUID playlistId, PlaylistItemAddDto dto, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isWritable(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Cannot add items to playlist " + playlistId)))
                .flatMap(pl -> mediaRepository.findByMediaId(dto.getMediaId())
                        .switchIfEmpty(Mono.error(new NoSuchMediaException(dto.getMediaId().toString())))
                        .flatMap(media -> {
                            if (dto.getPosition() == null) {
                                return playlistItemRepository.findMaxPositionByPlaylistId(playlistId)
                                        .map(maxPos -> maxPos + 1)
                                        .flatMap(targetPos -> playlistItemRepository.insertPlaylistItem(playlistId, media.getMediaId(), targetPos));
                            } else {
                                int targetPos = Math.max(0, dto.getPosition());
                                return playlistItemRepository.shiftPositionsUp(playlistId, targetPos)
                                        .then(playlistItemRepository.insertPlaylistItem(playlistId, media.getMediaId(), targetPos));
                            }
                        })
                        .then(getPlaylistDetails(playlistId, currentUserId))
                );
    }

    public Mono<PlaylistDetailResponseDto> removeMediaFromPlaylist(UUID playlistId, UUID mediaId, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isWritable(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Cannot remove items from playlist " + playlistId)))
                .flatMap(pl -> playlistItemRepository.findByPlaylistIdOrderByPositionAsc(playlistId)
                        .filter(item -> item.getMediaId().equals(mediaId))
                        .singleOrEmpty()
                        .switchIfEmpty(Mono.error(new NoSuchMediaException(mediaId.toString())))
                        .flatMap(targetItem -> playlistItemRepository.deleteByPlaylistIdAndMediaId(playlistId, mediaId)
                                .then(playlistItemRepository.shiftPositionsDown(playlistId, targetItem.getPosition()))
                        )
                        .then(getPlaylistDetails(playlistId, currentUserId))
                );
    }

    public Mono<PlaylistDetailResponseDto> reorderMediaInPlaylist(UUID playlistId, PlaylistItemReorderDto dto, UUID currentUserId) {
        return playlistRepository.findByPlaylistId(playlistId)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException(playlistId)))
                .filter(pl -> isWritable(pl, currentUserId))
                .switchIfEmpty(Mono.error(new PlaylistAccessDeniedException("Cannot reorder items in playlist " + playlistId)))
                .flatMap(pl -> playlistItemRepository.findByPlaylistIdOrderByPositionAsc(playlistId)
                        .collectList()
                        .flatMap(items -> {
                            int oldIndex = -1;
                            for (int i = 0; i < items.size(); i++) {
                                if (items.get(i).getMediaId().equals(dto.getMediaId())) {
                                    oldIndex = i;
                                    break;
                                }
                            }
                            if (oldIndex == -1) {
                                return Mono.error(new NoSuchMediaException(dto.getMediaId().toString()));
                            }
                            int newPos = Math.min(dto.getNewPosition(), Math.max(0, items.size() - 1));
                            if (oldIndex == newPos) {
                                return Mono.empty();
                            }
                            var itemToMove = items.remove(oldIndex);
                            items.add(newPos, itemToMove);

                            return playlistItemRepository.deleteByPlaylistId(playlistId)
                                    .thenMany(Flux.range(0, items.size())
                                            .flatMap(idx -> playlistItemRepository.insertPlaylistItem(
                                                    playlistId,
                                                    items.get(idx).getMediaId(),
                                                    idx
                                            ))
                                    ).then();
                        })
                        .then(getPlaylistDetails(playlistId, currentUserId))
                );
    }

    private boolean isOwner(Playlist playlist, UUID userId) {
        return userId != null && userId.equals(playlist.getUserId());
    }

    private boolean isReadable(Playlist playlist, UUID userId) {
        return playlist.isPublic() || isOwner(playlist, userId);
    }

    private boolean isWritable(Playlist playlist, UUID userId) {
        return isOwner(playlist, userId) || (playlist.isCollaborative() && playlist.isPublic());
    }

    private PlaylistResponseDto mapToResponseDto(Playlist playlist, Long itemCount) {
        return PlaylistResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .userId(playlist.getUserId())
                .title(playlist.getTitle())
                .description(playlist.getDescription())
                .isPublic(playlist.isPublic())
                .isCollaborative(playlist.isCollaborative())
                .itemCount(itemCount != null ? itemCount : 0L)
                .created(playlist.getCreated())
                .updated(playlist.getUpdated())
                .build();
    }
}