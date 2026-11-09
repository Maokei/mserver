package se.maokei.mserver.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.playlist.*;
import se.maokei.mserver.security.CustomAuthentication;
import se.maokei.mserver.services.PlaylistService;

import java.util.UUID;

@Tag(name = "Playlist", description = "Playlist management endpoints")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Operation(summary = "Create a new playlist")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public Mono<PlaylistResponseDto> createPlaylist(
            @Valid @RequestBody PlaylistCreateDto dto,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.createPlaylist(dto, userId);
    }

    @Operation(summary = "Get all public playlists")
    @GetMapping
    public Flux<PlaylistResponseDto> getPublicPlaylists() {
        return playlistService.getPublicPlaylists();
    }

    @Operation(summary = "Get current authenticated user's playlists")
    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public Flux<PlaylistResponseDto> getMyPlaylists(Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.getUserPlaylists(userId);
    }

    @Operation(summary = "Get playlist by ID")
    @GetMapping("/{id}")
    public Mono<PlaylistResponseDto> getPlaylistById(
            @PathVariable("id") UUID id,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.getPlaylist(id, userId);
    }

    @Operation(summary = "Get playlist details with media items")
    @GetMapping("/{id}/details")
    public Mono<PlaylistDetailResponseDto> getPlaylistDetails(
            @PathVariable("id") UUID id,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.getPlaylistDetails(id, userId);
    }

    @Operation(summary = "Update playlist info")
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Mono<PlaylistResponseDto> updatePlaylist(
            @PathVariable("id") UUID id,
            @RequestBody PlaylistUpdateDto dto,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.updatePlaylist(id, dto, userId);
    }

    @Operation(summary = "Delete playlist")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public Mono<Void> deletePlaylist(
            @PathVariable("id") UUID id,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.deletePlaylist(id, userId).then();
    }

    @Operation(summary = "Add media item to playlist")
    @PostMapping("/{id}/items")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public Mono<PlaylistDetailResponseDto> addMediaToPlaylist(
            @PathVariable("id") UUID id,
            @Valid @RequestBody PlaylistItemAddDto dto,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.addMediaToPlaylist(id, dto, userId);
    }

    @Operation(summary = "Remove media item from playlist")
    @DeleteMapping("/{id}/items/{mediaId}")
    @PreAuthorize("isAuthenticated()")
    public Mono<PlaylistDetailResponseDto> removeMediaFromPlaylist(
            @PathVariable("id") UUID id,
            @PathVariable("mediaId") UUID mediaId,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.removeMediaFromPlaylist(id, mediaId, userId);
    }

    @Operation(summary = "Reorder item in playlist")
    @PutMapping("/{id}/items/reorder")
    @PreAuthorize("isAuthenticated()")
    public Mono<PlaylistDetailResponseDto> reorderMediaInPlaylist(
            @PathVariable("id") UUID id,
            @Valid @RequestBody PlaylistItemReorderDto dto,
            Authentication auth) {
        UUID userId = extractUserId(auth);
        return playlistService.reorderMediaInPlaylist(id, dto, userId);
    }

    private UUID extractUserId(Authentication auth) {
        if (auth == null) {
            return null;
        }
        if (auth instanceof CustomAuthentication customAuth && customAuth.getUserId() != null) {
            try {
                return UUID.fromString(customAuth.getUserId());
            } catch (IllegalArgumentException ignored) {
            }
        }
        return null;
    }
}
