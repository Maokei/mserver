package se.maokei.mserver.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * FileRepository
 * <p>Writing files to disk</p>
 */
@Repository
public class FileRepository {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());
  String RESOURCE_DIR = FileRepository.class.getResource("/").getPath();
  private final Path basePath = Paths.get("./src/main/resources/upload/");

  /**
   * saveFile
   */
  public String saveFile(byte[] content, String fileName) throws Exception {
    Path newFile = Paths.get(RESOURCE_DIR + new Date().getTime() + "-" + fileName);
    Files.createDirectories(newFile.getParent());
    Files.write(newFile, content);

    return newFile.toAbsolutePath().toString();
  }

  public Mono<String> saveFile(Mono<FilePart> filePartMono) throws Exception {
    return filePartMono
        .doOnNext(fp -> LOGGER.info("FileRepository saving file: " + fp.filename()))
        .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename()))
            .then(Mono.just(basePath + File.separator + fp.filename())));
  }

  public FileSystemResource findFile(String location) {
    try {
      return new FileSystemResource(Paths.get(location));
    } catch (Exception e) {
      // Handle access or file not found problems.
      throw new RuntimeException();
    }
  }

  public Mono<FileSystemResource> findFileMono(String location) {
    try {
      return Mono.just(new FileSystemResource(Paths.get(location)));
    } catch (Exception e) {
      // Handle access or file not found problems.
      throw new RuntimeException();
    }
  }
}
