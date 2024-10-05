package se.maokei.mserver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.services.FileService;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Test
    public void saveAndFindTest() {
        String fid = "c7192772-0c1c-11ed-861d-0242ac120003";
        Media media = Media.builder().location("file:test_files/audio.mp3").filename("song.mp3").title("HxH Piano").foreignId(fid).build();

        //TODO fileService.save(Mono.just(media));
    }
}
