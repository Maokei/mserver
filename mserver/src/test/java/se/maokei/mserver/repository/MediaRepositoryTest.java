package se.maokei.mserver.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.model.Media;

import java.util.HashMap;
import java.util.UUID;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MediaRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void saveMedia() {
        Media media = new Media();
        String title = "first media";
        HashMap<String, String> metadata = new HashMap<>();
        metadata.put("first", "HelloWorld");
        metadata.put("second", "Hello");
        media.setForeignId("");
        media.setTitle(title);
        media.setUserId(UUID.randomUUID());
        media.setMetadata(metadata);
        media.setType(Media.MediaType.AUDIO);
        media.setFilename("first media");
        media.setSize(333L);
        String content = "Mock content";
        media.setContent(content.getBytes());
        media.setLocation("test location");
        media.setViews(0);
        media.setUrl("test url");

        Mono<Media> persistedMedia = mediaRepository.save(media)
            .doOnNext(m -> mediaRepository.findById(m.getId())
        );

        StepVerifier.create(persistedMedia)
            .assertNext(m -> {
                Assertions.assertNotNull(m.getId(), "Should have a UUID");
                Assertions.assertEquals(title, m.getTitle());
                Assertions.assertEquals(2, m.getMetadata().size(), "Metadata should have two items");
                Assertions.assertEquals("HelloWorld", m.getMetadata().get("first"));
            })
            .expectComplete().verify();
    }
}