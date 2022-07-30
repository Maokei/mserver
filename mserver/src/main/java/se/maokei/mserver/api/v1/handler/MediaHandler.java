package se.maokei.mserver.api.v1.handler;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.services.StreamingService;

@AllArgsConstructor
@Component
public class MediaHandler {
  private final StreamingService streamingService;
  private final MediaRepository mediaRepository;

  public Mono<ServerResponse> hello(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(new Media()));
  }

  public Mono<ServerResponse> listAllMedia(ServerRequest req) {
    Flux<Media> mediaFlux = mediaRepository.findAll();
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .cacheControl(CacheControl.noCache())
        .location(req.uri())
        .body(mediaFlux, Media.class);
  }

  public Mono<ServerResponse> getMedia(ServerRequest req) {
    final String foreignId = req.pathVariable("foreignId");
    return ServerResponse.ok()
        .body(streamingService.getMedia(foreignId), Resource.class);
  }
}
