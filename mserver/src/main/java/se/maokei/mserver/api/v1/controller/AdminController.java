package se.maokei.mserver.api.v1.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import se.maokei.mserver.services.LoggedUserTracker;

import java.time.Duration;
import java.time.LocalDateTime;

//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final LoggedUserTracker tracker;

    public AdminController(LoggedUserTracker tracker) {
        this.tracker = tracker;
    }

    @GetMapping("/logged-users")
    public Flux<ServerSentEvent> getLoggedUsers() {
        return tracker.getLoggedUsers();
    }

    @GetMapping("/events")
    public Flux<ServerSentEvent<String>> getEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("message")
                        .data("Event #" + sequence + " at " + LocalDateTime.now())
                        .build());
    }

    /*@Autowired
    SseTemplate template;

    @GetMapping(path = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sseUsers() {
        return template.newSseEmitter("users");
    }*/
}