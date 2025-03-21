package se.maokei.mserver.controllerv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.dto.UserRegisterDto;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class RegisterControllerTest {
    private static final String REGISTER_URL = "/api/v1/register";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void registerNewUser() {
        String username = "test1";
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername(username);
        dto.setPassword("12345e");
        dto.setMatchingPassword("12345e");
        dto.setEmail(username + "@email.com");
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
                .expectStatus()
                .isBadRequest()
                .expectHeader()
                .contentType(APPLICATION_JSON)
                .expectBody()
                .json("[\"Passwords don't match\"]");
    }

    //TODO @Test
    public void registerAlreadyExistingEmail() {
        final String EMAIL = "test@gmail.com";
        String username1 = String.valueOf(UUID.randomUUID());
        String username2 = String.valueOf(UUID.randomUUID());
        UserRegisterDto dto = new UserRegisterDto();

        dto.setUsername(username1);
        dto.setPassword("12345e");
        dto.setMatchingPassword("12345e");
        dto.setEmail(EMAIL);
        webTestClient.post()
                .uri(REGISTER_URL)
                .body(Mono.just(dto), UserRegisterDto.class)
                .exchange()
                .expectStatus().isOk();


    }

    //TODO @Test
    public void registerUserAlreadyExistingUsername() {

    }
}
