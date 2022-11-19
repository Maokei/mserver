package se.maokei.mserver.api.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Playlist;
import se.maokei.mserver.services.PlaylistService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PlaylistController {
    private PlaylistService playlistService;
    @GetMapping("/playlist")
    public Flux<Playlist> getAllPlaylists() {
        return this.playlistService.getAllPlaylists();
    }

    @GetMapping("/playlist/{id}")
    public Mono<Playlist> getPlaylistById(@PathVariable("id") String id) {
        return this.playlistService.getPlaylist(id);
    }

}
