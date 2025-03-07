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

  public Mono<Playlist> getPlaylist(UUID id) {
    return this.playlistRepository.findById(id).flatMap(found -> this.mediaRepository
            .findAllById(found.getMedias())
            .collectList()
            .doOnNext(found::setMedia)
            .then(Mono.just(found)));
  }
  public void deletePlaylist() {
  }

  public void updatePlaylist() {

  }

  public void addPlaylist(Playlist pl) {

  }
}
