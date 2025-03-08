package se.maokei.mserver.controllerv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.maokei.mserver.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class PingControllerTest {
    private static final String URL = "/ping";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getPingTest() {
        webTestClient.get()
                .uri(URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class).isEqualTo("alive");
    }
}