package se.maokei.mserver.api.v1.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.UserRegisterDto;
import se.maokei.mserver.model.User;
import se.maokei.mserver.services.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
//@RequestMapping("/auth")
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserService userService;

    @PostMapping("/user/registration")
    public Mono<User> registerAccount(@Valid @RequestBody UserRegisterDto dto) {
        LOGGER.debug("User account registration dto: {}", dto);
        /*return userService.registerNewUser(dto).flatMap(usr -> {
            System.out.println("god something " + usr);
            //return Mono.just("New user registered");
        }).subscribe().defaultIfEmpty("");*/
        return userService.registerNewUser(dto);
    }
}
