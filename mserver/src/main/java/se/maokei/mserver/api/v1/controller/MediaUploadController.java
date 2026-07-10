package se.maokei.mserver.api.v1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.services.FileService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class MediaUploadController {
  private final Logger log = org.slf4j.LoggerFactory.getLogger(MediaUploadController.class);
  private FileService fileService;
  private final Path basePath = Paths.get("./src/main/resources/upload/");

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/media/single")
  public Mono<ResponseEntity<String>> upload(@RequestPart("file") Mono<FilePart> filePartMono){
    //TODO create media, use file service
    return  filePartMono
        .doOnNext(fp -> log.info("Received single file: {}", fp.filename()))
        .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
        .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/media/upload")
  public Mono<Void> upload(@RequestPart("files") Flux<FilePart> partFlux){
    log.info("Files count: {}", partFlux.count());
    return  partFlux
        .doOnNext(fp -> log.info("Received files: {}", fp.filename()))
        .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
        .then();
  }

  @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
  public Mono<FileSystemResource> downloadImage(@PathVariable UUID imageId) throws Exception {
    return fileService.monoFind(Mono.just(imageId));
  }
}