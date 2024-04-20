package se.maokei.mserver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import se.maokei.mserver.BaseIT;
import se.maokei.mserver.model.Media;
import se.maokei.mserver.services.FileService;

@SpringBootTest
public class FileServiceTest extends BaseIT {
    @Autowired
    private FileService fileService;
    @Test
    public void saveAndFindTest() {
        String fid = "c7192772-0c1c-11ed-861d-0242ac120003";
        Media media = Media.builder().location("file:test_files/audio.mp3").fileName("song.mp3").title("HxH Piano").foreignId(fid).build();

        //fileService.save(Mono.just(media));
    }
}
