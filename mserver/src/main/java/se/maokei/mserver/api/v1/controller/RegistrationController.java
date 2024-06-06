package se.maokei.mserver.api.v1.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.UserRegisterDto;
import se.maokei.mserver.services.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
@OpenAPIDefinition(info = @Info(
        title = "RegistrationController",
        description = "User registration API"
),
        tags = {
            @Tag(name = "Registration")
        }
)
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register a new User")
    })
    @PostMapping("/register")
    public Mono<ResponseEntity<?>> registerAccount(@Valid @RequestBody UserRegisterDto dto) {
        LOGGER.debug("User account registration dto: {}", dto);
        return userService.registerNewUser(dto)
                .flatMap(res -> Mono.just(ResponseEntity.ok().body(res)));
    }
}