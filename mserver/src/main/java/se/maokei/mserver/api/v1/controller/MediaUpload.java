package se.maokei.mserver.api.v1.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.services.FileService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class MediaUpload {
  private FileService fileService;
  private final Path basePath = Paths.get("./src/main/resources/upload/");

  @PostMapping("media/single") //broken
  public Mono<Void> upload(@RequestPart("fileToUpload") Mono<FilePart> filePartMono){
    //TODO create media, use file service
    return  filePartMono
        .doOnNext(fp -> System.out.println("Received File : " + fp.filename()))
        .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
        .then();
  }

  @PostMapping("media/multi")
  public Mono<Void> upload(@RequestPart("files") Flux<FilePart> partFlux){
    return  partFlux
        .doOnNext(fp -> System.out.println(fp.filename()))
        .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
        .then();
  }

  @Valid
  @RequestMapping(value = "/saveFile")
  public @ResponseBody String storeAd(@NotNull @RequestPart ("model") String adString, @NotNull @RequestPart ("file") FilePart file) throws IOException {
    System.out.println("adString > "+adString);
    System.out.println(file.filename());
    return file.filename();
  }

  @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
  public Mono<FileSystemResource> downloadImage(@PathVariable UUID imageId) throws Exception {
    return fileService.monoFind(Mono.just(imageId));
  }
}