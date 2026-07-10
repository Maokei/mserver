package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.security.CustomAuthentication;

import java.security.Principal;

@AllArgsConstructor
@Service
public class StreamingService {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private final MediaRepository mediaRepository;
  private final ResourceLoader resourceLoader;

  public Mono<Resource> getMedia(String foreignId, Mono<Principal> principal) {
    return mediaRepository.findByForeignId(foreignId)
        .flatMap(media -> principal
            .flatMap(p -> {
              String userId = ((CustomAuthentication) p).getUserId();
              log.info("StreamingService: User: {} UserId: {} ,requested media with id({}) ", p.getName(), userId, foreignId);
              return Mono.fromSupplier(() -> resourceLoader.getResource(media.getLocation()));
            })
            .switchIfEmpty(Mono.defer(() -> {
              if (media.getUserId() == null) { //todo public or not
                log.info("StreamingService: Public request for media({}) with id({}) ", media.getType().name(), foreignId);
                return Mono.fromSupplier(() -> resourceLoader.getResource(media.getLocation()));
              } else {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Media is not public"));
              }
            }))
        ).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found")));
  }
}