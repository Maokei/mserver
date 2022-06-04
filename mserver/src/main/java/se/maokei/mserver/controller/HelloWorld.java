package se.maokei.mserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.services.StreamingService;

import java.io.InputStream;
import java.nio.file.Path;
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
        return service.getAudio("song");
    }

    @GetMapping(path = "/videoTest", produces = "video/mp4")
    public Mono<Resource> getVideo(@RequestHeader("Range") String range) {
        //@PathVariable String title,
        System.out.println(range);
        return service.getVideo("video");
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
}
