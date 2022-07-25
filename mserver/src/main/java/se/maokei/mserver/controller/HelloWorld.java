package se.maokei.mserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.repository.MediaRepository;
import se.maokei.mserver.services.FileService;
import se.maokei.mserver.services.StreamingService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

/**
 * Controller for testing
 * */
@RestController
@Tag(name = "Test API", description = "API for testing purpose")
public class HelloWorld {
    @GetMapping("/ping")
    public Mono<String> ping(Mono<Principal> principal) {
        return Mono.just("alive");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/protected")
    public Mono<String> protectedPing(Mono<Principal> principal) {
        return Mono.just("alive");
    }

    @GetMapping("/hello")
    @Operation(description = "Hello world, returns a hello to logged in user")
    public Mono<String> greet(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Hello, %s", name));
    }

    @PostMapping("/bonk")
    public Mono<String> bonk(Mono<Principal> principal) {
        System.out.println("bonk");
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Hello, %s", name));
    }

    /*@GetMapping("/audio")
    @Operation(description = "Testing of audio stream single song")
    public Mono<Resource> audio() {
        String produces = "audio/mp3";

        //InputStream ioStream = this.getClass()
        //        .getClassLoader().getResourceAsStream();

        Path path = Path.of("/home/maokei/IdeaProjects/mserver/mserver/test_files/audio.mp3");
        System.out.println(path);

        //Create a ClassPathResource, for example
        return FileSystemResource(path);
    }*/

    @Autowired
    private StreamingService service;

    //value = "audio/{title}",
    @GetMapping(path = "/audioTest", produces = "audio/mp4")
    public Mono<Resource> getAudio(@RequestHeader("Range") String range) {
        //@PathVariable String title,
        System.out.println(range);
        return service.getTestAudio("song");
    }

    @GetMapping(path = "/videoTest", produces = "video/mp4")
    public Mono<Resource> getVideo(@RequestHeader("Range") String range) {
        //@PathVariable String title,
        System.out.println(range);
        return service.getTestVideo("video");
    }

    /*public Mono<ResponseEntity<byte[]>> audioTwo(@RequestHeader(value = "Range", required = false) String httpRangeList) {
        //TODO getContent
        return Mono.justOrEmpty(null);
    }*/

    /*@GetMapping("/video")
    @Operation(description = "Testing of video stream single song")
    public Mono<Resource> video() {
        String produces = "audio/mp4";

    }*/

    //file upload test
    @Autowired
    FileService fileService;
    @Autowired
    MediaRepository mediaRepository;

    @GetMapping("/all")
    public Flux<Media> listAll() throws Exception {
        return mediaRepository.findAll();
    }

    @GetMapping(value = "video/{foreignId}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String foreignId, @RequestHeader("Range") String range) {
        System.out.println("range in bytes() : " + range);
        return service.getVideo(foreignId);
    }

    @GetMapping(path = "/videoReal/{id}", produces = "video/mp4")
    public Mono<Resource> getRealVideo(@PathVariable String id, @RequestHeader(value = "Range") String range) {
        System.out.println(id + " " + range);
        return service.getVideo(id);
    }

    @PostMapping("/image")
    public Mono<String> uploadImage(@Valid @RequestPart("image") Mono<FilePart> fileMono) throws Exception {
        return fileService.save(fileMono);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") Mono<FilePart> file) {
        Path basePath = Paths.get("/home/maokei/");
        //String fileName = file.getOriginalFilename();
        //String fileName = String.valueOf(file.subscribe(s -> s.filename()));
        String fileName = "fake.jpg";
        System.out.println("/upload " + fileName);
        try {
            //file.transferTo( new File("/home/maokei/" + fileName));
            file.doOnNext(fp -> System.out.println("Received File : " + fp.filename()))
                    .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
                    .then();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @PostMapping("/single")
    public Mono<Void> singleUpload(@RequestParam("upload")Mono<FilePart> fileMono) {
        Path path = Paths.get("/home/maokei/");
        return fileMono.doOnNext(f -> System.out.println("filename: " + f.filename()))
                .flatMap(f -> f.transferTo(path.resolve(f.filename())))
                .then();
    }

    private final Path basePath = Paths.get("./src/main/resources/upload/");

    @PostMapping("file/single")
    public Mono<Void> upload(@RequestPart("user-name") String name,
                             @RequestPart("fileToUpload") Mono<FilePart> filePartMono){
        System.out.println("user : " + name);

        return  filePartMono
                .doOnNext(fp -> System.out.println("Received File : " + fp.filename()))
                .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
                .then();
    }

    @PostMapping("file/multi1")
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
    public Mono<FileSystemResource> downloadImage(@PathVariable String imageId) throws Exception {
        return fileService.monoFind(imageId);
    }

    @PostMapping("/uploader")
    public Mono<String> upload(@RequestPart("file") FilePart filePart) throws Exception {
        String fileName = System.nanoTime() + "-" + filePart.filename();
        System.out.println("2222");
        return Mono.just("2 " + fileName);
    }
}
