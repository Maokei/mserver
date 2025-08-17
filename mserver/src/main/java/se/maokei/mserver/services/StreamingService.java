package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.repository.UserRepository;
import se.maokei.mserver.security.CustomAuthentication;

import java.security.Principal;

@AllArgsConstructor
@Service
public class StreamingService {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private static final String AUDIO_FORMAT = "classpath:audios/%s.mp3";
  private static final String VIDEO_FORMAT = "classpath:videos/%s.mp4";
  private final UserRepository userRepository;
  private FileService fileService;
  private MediaRepository mediaRepository;
  private UserService userService;
  private ResourceLoader resourceLoader;

  public Mono<Resource> getMedia(String foreignId, Mono<Principal> principal) {
    return principal.doOnNext(p -> {
        log.info("StreamingService: User {} requested video id({}) ", p.getName(), foreignId);
    })
    .zipWith(mediaRepository.findByForeignId(foreignId))
    .flatMap(tuple -> {
        Media media = tuple.getT2();
        String userId = ((CustomAuthentication) tuple.getT1()).getUserId();

        return Mono.fromSupplier(() -> resourceLoader.getResource(media.getLocation()));
    });
  }
}