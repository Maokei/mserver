package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.repository.MediaRepository;

@AllArgsConstructor
@Service
public class StreamingService {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());
  private static final String AUDIO_FORMAT = "classpath:audios/%s.mp3";
  private static final String VIDEO_FORMAT = "classpath:videos/%s.mp4";
  private FileService fileService;
  private MediaRepository mediaRepository;
  private ResourceLoader resourceLoader;

  public Mono<Resource> getMedia(String foreignId) {
    LOGGER.info("StreamingService requested video id " + foreignId);
    return mediaRepository.findByForeignId(foreignId)
        .flatMap(
            media -> Mono.fromSupplier(
                () -> resourceLoader.getResource(media.getLocation()
                )
            )
        );
  }
}