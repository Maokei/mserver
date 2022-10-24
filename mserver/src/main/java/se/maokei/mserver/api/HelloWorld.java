package se.maokei.mserver.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import se.maokei.mserver.services.StreamingService;

import java.security.Principal;

/**
 * Controller for testing
 * */
@RestController
@Tag(name = "Test API", description = "API for testing purpose")
public class HelloWorld {
    private StreamingService streamingService;
    public HelloWorld(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

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
}
