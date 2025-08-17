package se.maokei.mserver.api.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.User;
import se.maokei.mserver.services.UserService;

@RestController("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN') || (principal.id == #id)")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User newInfo, @PathVariable Long id) {
        // TODO: Update user
        return Mono.just(ResponseEntity.ok(newInfo));
    }
}
