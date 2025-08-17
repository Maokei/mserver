package se.maokei.mserver.api.v1.handler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.PageRequest;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.services.StreamingService;

import java.security.Principal;

@RequiredArgsConstructor
@Component
@OpenAPIDefinition(info = @Info(
        title = "Media API",
        description = "API for getting media and list media."
  )
)
public class MediaHandler {
  private static final Logger log = LoggerFactory.getLogger(MediaHandler.class);
  private final StreamingService streamingService;
  private final MediaRepository mediaRepository;

  @Operation
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "List of media", content = {
      @Content(mediaType = "application/json")
      })
  })
  public Mono<ServerResponse> listAllMedia(ServerRequest req) {
    Flux<Media> mediaFlux = mediaRepository.findAll(Sort.unsorted());
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .cacheControl(CacheControl.noCache())
        .location(req.uri())
        .body(mediaFlux, Media.class).log();
  }

  @Operation
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Pageable media", content = {
      @Content(mediaType = "application/json")
    })
  })
  public Mono<ServerResponse> listMedia(ServerRequest req) { //TODO keep this?
    Flux<Media> mediaFlux = req.bodyToMono(PageRequest.class)
        .flatMapMany(pr -> mediaRepository.findAllBy(pr).log());
    return ServerResponse.ok().body(mediaFlux, Media.class);
  }

  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  public Mono<ServerResponse> getMedia(ServerRequest req) {
    Mono<Principal> principal = req.exchange().getPrincipal();
    final String foreignId = req.pathVariable("foreignId");
    return ServerResponse.ok()
        .body(streamingService.getMedia(foreignId, principal), Resource.class);
  }
}
