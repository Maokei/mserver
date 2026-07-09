package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.repository.FileRepository;
import se.maokei.mserver.repository.MediaRepository;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class FileService {
  //todo work in progress
  //@Value("${app.file.location}")
  //private String defaultLocation;
  private FileRepository fileRepository;
  private MediaRepository mediaRepository;

    // find files with no references
    // every file must be owned by someone
    //

  public Mono<String> storeFile(Mono<FilePart> fileMono) throws Exception {
    Mono<Media> mediaMono = fileMono.flatMap(f -> {
      String name = f.filename();
      String fId = UUID.randomUUID().toString();
      log.info("FileService, save:  {} {}", name, fId);
      Media media = Media.builder().filename(name).foreignId(fId).build();
      return Mono.just(media);
    });
    return mediaMono.flatMap(m -> {
      try {
        return fileRepository.saveFile(fileMono, m);
      } catch (Exception e) {
        return Mono.error(new RuntimeException(e));
      }
    }).doOnNext(mediaRepository::save).flatMap(m -> {
      String location = m.getLocation();
      return Mono.just(location);
    });
    /*mediaMono.flatMap(m -> {
      try {
        fileRepository.saveFile(fileMono, m);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    })*/

        /*Mono<String> location = fileRepository.saveFile(fileMono);
        return location.flatMap(locRes -> {
            LOGGER.info("FileService, saving: {}", locRes);
            Image img = new Image();
            img.setLocation(locRes);
            img.setName("test");
            return imageDbRepository.save(img);
        }).flatMap(res -> Mono.just(res.getId()));*/
  }

  public String storeFile(byte[] bytes, String fileName) throws Exception {
    //String location = fileRepository.saveFile(bytes, fileName);
    String location = "classpath:videos/video.mp4";
    String fId = UUID.randomUUID().toString();
    Media media = Media.builder().filename(fileName).foreignId(fId).location(location).build();
    mediaRepository.save(media).subscribe();
    //return imageDbRepository.save(media).subscribe(EntityMetadata::getId).toString();
    return location + " " + fId;
  }

  public FileSystemResource findImageById(UUID imageId) {
        /*Image image = imageDbRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));*/
    //return fileRepository.findFile(image.getLocation());
    return (FileSystemResource) mediaRepository.findByMediaId(imageId).subscribe(res -> fileRepository.findFile(res.getLocation()));
  }

  public Mono<FileSystemResource> monoFind(Mono<UUID> imageId) {
    return imageId.flatMap(id -> mediaRepository.findByMediaId(id).flatMap(
        metadata -> fileRepository.findFileMono(metadata.getLocation())
        )
    );
  }
}
