package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Playlist;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.repository.PlaylistRepository;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class PlaylistService {
  private final PlaylistRepository playlistRepository;
  private final MediaRepository mediaRepository;

  public Flux<Playlist> getAllPlaylists() {
    return this.playlistRepository.findAll();
  }

  public Mono<Playlist> getPlaylist(UUID playlistId) {
    return this.playlistRepository.findById(playlistId)
      .doOnNext(pl -> pl.getMediaIds().stream().parallel().map(mediaRepository::findById).forEach(Mono::subscribe));
  }
  public Mono<UUID> deletePlaylist(UUID playlistId) {
    return this.playlistRepository.deleteById(playlistId).thenReturn(playlistId);
  }

  public Mono<Playlist> updatePlaylist(Playlist playlist) {
    return this.playlistRepository.save(playlist);
  }

  public Mono<Playlist> addMediaToPlaylist(UUID playlistID, UUID mediaID) {
    return this.playlistRepository.findById(playlistID).doOnNext(pl -> pl.addMediaId(mediaID)).flatMap(this.playlistRepository::save);
  }
}