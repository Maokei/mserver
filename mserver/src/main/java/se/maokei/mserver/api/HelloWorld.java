package se.maokei.mserver.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

/**
 * Controller for testing
 * */
@RestController
@Tag(name = "Test API", description = "API for testing purpose")
public class HelloWorld {

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
