package se.maokei.mserver.controllerv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.AuthRequest;

@SpringBootTest
@AutoConfigureWebTestClient
public class AuthControllerTest {
    private final String LOGIN_URL = "/api/v1/login";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void attemptLoginSuccessUser() throws Exception {
        AuthRequest dto = new AuthRequest();
        dto.setUsername("user");
        dto.setPassword("password");
        this.webTestClient.post().uri(LOGIN_URL).contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), AuthRequest.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void attemptLoginFailureUser() throws Exception {
        AuthRequest dto = new AuthRequest();
        dto.setUsername("user");
        dto.setPassword("");
        this.webTestClient.post().uri(LOGIN_URL).contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), AuthRequest.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void attemptLoginSuccessAdmin() throws Exception {
        AuthRequest dto = new AuthRequest();
        dto.setUsername("admin");
        dto.setPassword("password");
        this.webTestClient.post().uri(LOGIN_URL).contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), AuthRequest.class)
                .exchange()
                .expectStatus().isOk();
    }
}
