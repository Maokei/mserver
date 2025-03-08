package se.maokei.mserver.services;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class LoggedUserTracker {
    private final Sinks.Many<ServerSentEvent> sink = Sinks.many().replay().latest();
    private final List<String> loggedUsers = new CopyOnWriteArrayList<>();

    public Flux<ServerSentEvent> getLoggedUsers() {
        return sink.asFlux();
    }

    public void addUser(String username) {
        loggedUsers.add(username);
        sink.tryEmitNext(ServerSentEvent.builder()
                .event("userLoggedIn")
                .data(username)
                .build());
    }

    public void removeUser(String username) {
        loggedUsers.remove(username);
        sink.tryEmitNext(ServerSentEvent.builder()
                .event("userLoggedOut")
                .data(username)
                .build());
    }
}