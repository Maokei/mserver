package se.maokei.mserver.controllerv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.UserRegisterDto;

@SpringBootTest
@AutoConfigureWebTestClient
public class RegisterControllerTest {
    private static final String REGISTER_URL = "/api/v1/register";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void registerNewUser() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("BigCat");
        dto.setPassword("12345e");
        dto.setMatchingPassword("12345e");
        dto.setEmail("bigcat@gmail.com");
        webTestClient.post()
                .uri(REGISTER_URL)
                .body(Mono.just(dto), UserRegisterDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void registerUserNotMatchingPassword() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("SmallCat");
        dto.setPassword("12345e");
        dto.setMatchingPassword("1akjnasad");
        dto.setEmail("smallcat@gmail.com");
        webTestClient.post()
                .uri(REGISTER_URL)
                .body(Mono.just(dto), UserRegisterDto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
