package se.maokei.mserver.controllerv1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.dto.AuthRequest;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;
import se.maokei.mserver.repository.UserRepository;

import java.util.List;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWebTestClient
public class AuthControllerTest {
    private final String LOGIN_URL = "/api/v1/login";
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeAll
    public void setup() {
        this.userRepository.deleteAll().subscribe();
        User admin = new User("admin", passwordEncoder.encode("password"), "admin@gmail.com", List.of(Role.ROLE_ADMIN), true, true);
        User user = new User("user", passwordEncoder.encode("password"), "user@gmail.com", List.of(Role.ROLE_USER), true, true);
        this.userRepository.save(admin).subscribe();
        this.userRepository.save(user).subscribe();
    }

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
