package se.maokei.mserver.controller.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.services.StreamingService;

import javax.validation.Valid;

@Tag(name = "Media API", description = "API for managing media")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/media")
public class MediaController {
  private StreamingService streamingService;

  @PostMapping("/upload")
  public Mono<String> uploadImage(@Valid @RequestPart("file") FilePart filePart) throws Exception {
    String okay = "okay: " + filePart.filename() + " ";
    return Mono.just(okay);
  }
}
