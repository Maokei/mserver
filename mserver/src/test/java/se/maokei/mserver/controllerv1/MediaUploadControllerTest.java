package se.maokei.mserver.controllerv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import se.maokei.mserver.TestcontainersConfiguration;

import java.io.IOException;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class MediaUploadControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser(roles = "USER")
    public void uploadTest() throws IOException {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        builder.part("file", new FileSystemResource("test_files/audio.mp3").getInputStream().readAllBytes())
                .header("Content-Disposition", "form-data; name=fileToUpload; filename=song.mp3");

        webTestClient.post()
                .uri("/api/v1/media/single")
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isCreated();
    }
}
