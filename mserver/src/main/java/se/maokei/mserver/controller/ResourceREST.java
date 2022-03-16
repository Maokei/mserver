package se.maokei.mserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.Message;

@RestController
public class ResourceREST {

  @GetMapping("/res/user")
  @PreAuthorize("hasRole('USER')")
  public Mono<ResponseEntity<Message>> user() {
    return Mono.just(ResponseEntity.ok(new Message("Content for user")));
  }

  @GetMapping("/res/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public Mono<ResponseEntity<Message>> admin() {
    return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
  }

  @GetMapping("/res/user-or-admin")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public Mono<ResponseEntity<Message>> userOrAdmin() {
    return Mono.just(ResponseEntity.ok(new Message("Content for user or admin")));
  }
}