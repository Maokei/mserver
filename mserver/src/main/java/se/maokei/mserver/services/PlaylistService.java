package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.maokei.mserver.model.Playlist;
import se.maokei.mserver.repository.PlaylistRepository;

@Service
@Transactional
@AllArgsConstructor
public class PlaylistService {
  private final PlaylistRepository playlistRepository;
  public void getAllPlaylists() {

  }

  public void getPlaylist(int id) {

  }
  public void deletePlaylist() {

  }

  public void updatePlaylist() {

  }

  public void addPlaylist(Playlist pl) {

  }
}
